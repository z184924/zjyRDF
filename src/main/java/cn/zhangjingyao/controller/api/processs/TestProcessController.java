package cn.zhangjingyao.controller.api.processs;

import cn.zhangjingyao.controller.base.BaseController;
import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.entity.system.User;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/api/testProcess")
public class TestProcessController extends BaseController {
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private FormService formService;
    @Autowired
    private HistoryService historyService;

    @ResponseBody
    @RequestMapping(value = "/getAllResource", produces = "application/json;charset=UTF-8")
    public String getAllResource() throws Exception {
        logBefore(logger, "查询全部流程定义");
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().latestVersion().list();
        List<PageData> resultList=new ArrayList<>();
        for (ProcessDefinition processDefinition: processDefinitionList){
            PageData pageData=new PageData();
            pageData.put("id",processDefinition.getId());
            pageData.put("key",processDefinition.getKey());
            pageData.put("name",processDefinition.getName());
            pageData.put("version",processDefinition.getVersion());
            pageData.put("engineVersion",processDefinition.getEngineVersion());
            resultList.add(pageData);
        }
        PageData resultData=new PageData();
        resultData.put("resultList",resultList);
        return this.jsonContent("success",resultData);
    }


    @ResponseBody
    @RequestMapping(value = "/getMyTask", produces = "application/json;charset=UTF-8")
    public String getMyTask() throws Exception {
        logBefore(logger, "查询我的任务");
        User currentUser = this.getCurrentUser();
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(currentUser.getAccount()).list();
        List<PageData> resultList = new ArrayList<>();
        for (Task t:taskList){
            PageData task=new PageData();
            task.put("id",t.getId());
            task.put("name",t.getName());
            task.put("createTime",t.getCreateTime());
            task.put("formProperties",formService.getTaskFormData(t.getId()).getFormProperties());
            resultList.add(task);
        }
        PageData resultMap=new PageData();
        resultMap.put("taskList",resultList);
        return this.jsonContent("success",resultMap);
    }
    @ResponseBody
    @RequestMapping(value = "/getTaskInfo", produces = "application/json;charset=UTF-8")
    public String getTaskInfo() throws Exception {
        logBefore(logger, "查询任务");
        User currentUser = this.getCurrentUser();
        PageData pageData = this.getPageData();
        List<Task> taskList = taskService.createTaskQuery().taskId(pageData.getString("taskId")).list();
        List<PageData> resultList = new ArrayList<>();
        PageData task=new PageData();
        if(taskList!=null&&!taskList.isEmpty()){
            Task t=taskList.get(0);
            task.put("id",t.getId());
            task.put("name",t.getName());
            task.put("createTime",t.getCreateTime());
            task.put("formProperties",formService.getTaskFormData(t.getId()).getFormProperties());
        }
        PageData resultMap=new PageData();
        resultMap.put("taskInfo",task);
        return this.jsonContent("success",resultMap);
    }

    @ResponseBody
    @RequestMapping(value = "/startProcess", produces = "application/json;charset=UTF-8")
    public String start() throws Exception {
        logBefore(logger, "启动流程");
        PageData pd=this.getPageData();
        runtimeService.startProcessInstanceById(pd.getString("processId"));
        return this.jsonContent("success", "启动成功");
    }

    @ResponseBody
    @RequestMapping(value = "/finishTask", produces = "application/json;charset=UTF-8")
    public String finishTask() throws Exception {
        logBefore(logger, "完成任务");
        PageData pd=this.getPageData();
        pd.remove("token");
        String taskId=pd.getString("taskId");
        pd.remove("taskId");
        Set<String> keySet = pd.keySet();
        for(String key:keySet){
            taskService.setVariable(taskId,key,pd.get(key));
        }
        taskService.complete(taskId);
        return this.jsonContent("success", "任务已完成");
    }

    @ResponseBody
    @RequestMapping(value = "/getProcessDiagram", produces = "application/json;charset=UTF-8")
    public String getProcessDiagram(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        logBefore(logger, "获取流程图");
        PageData pd=this.getPageData();
        List<Task> taskList = taskService.createTaskQuery().taskId(pd.getString("taskId")).list();
        Task task=null;
        if(taskList!=null&&!taskList.isEmpty()){
            task=taskList.get(0);
        }
        //获取历史流程实例
        HistoricProcessInstance processInstance =  historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());

        List<HistoricActivityInstance> highLightedActivitList =  historyService.createHistoricActivityInstanceQuery().processInstanceId(task.getProcessInstanceId()).list();
        //高亮环节id集合
        List<String> highLightedActivities = new ArrayList<String>();
        for(HistoricActivityInstance tempActivity : highLightedActivitList){
            String activityId = tempActivity.getActivityId();
            highLightedActivities.add(activityId);
        }
        //高亮线路id集合
        List<String> highLightedFlows = getHighLightedFlows(bpmnModel,(ProcessDefinitionEntity) repositoryService.getProcessDefinition(task.getProcessDefinitionId()), highLightedActivitList);
        ProcessDiagramGenerator processDiagramGenerator = new DefaultProcessDiagramGenerator();
        //中文显示的是口口口，设置字体就好了
        InputStream processDiagramIs = processDiagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivities,highLightedFlows,"宋体","宋体","宋体",null,1.0);
        httpServletResponse.setContentType("image/png");
        OutputStream os = httpServletResponse.getOutputStream();
        byte[] tempBytes=new byte[1000];
        while ((processDiagramIs.read(tempBytes)) != -1) {
            os.write(tempBytes);
        }
        processDiagramIs.close();
        os.flush();
        os.close();
        return "success";
    }

    /**
     * 获取高亮线路ID
     * @param bpmnModel
     * @param processDefinitionEntity
     * @param historicActivityInstances
     * @return
     */
    private List<String> getHighLightedFlows(BpmnModel bpmnModel, ProcessDefinitionEntity processDefinitionEntity, List<HistoricActivityInstance> historicActivityInstances) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //24小时制
        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {
            // 对历史流程节点进行遍历
            // 得到节点定义的详细信息
            FlowNode activityImpl = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstances.get(i).getActivityId());
            List<FlowNode> sameStartTimeNodes = new ArrayList<FlowNode>();// 用以保存后续开始时间相同的节点
            FlowNode sameActivityImpl1 = null;
            HistoricActivityInstance activityImpl_ = historicActivityInstances.get(i);// 第一个节点
            HistoricActivityInstance activityImp2_;
            for (int k = i + 1; k <= historicActivityInstances.size() - 1; k++) {
                activityImp2_ = historicActivityInstances.get(k);// 后续第1个节点
                if (activityImpl_.getActivityType().equals("userTask") && activityImp2_.getActivityType().equals("userTask") &&
                        df.format(activityImpl_.getStartTime()).equals(df.format(activityImp2_.getStartTime()))) //都是usertask，且主节点与后续节点的开始时间相同，说明不是真实的后继节点
                {
                } else {
                    sameActivityImpl1 = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstances.get(k).getActivityId());//找到紧跟在后面的一个节点
                    break;
                }
            }
            sameStartTimeNodes.add(sameActivityImpl1); // 将后面第一个节点放在时间相同节点的集合里
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);// 后续第二个节点
                if (df.format(activityImpl1.getStartTime()).equals(df.format(activityImpl2.getStartTime()))) {// 如果第一个节点和第二个节点开始时间相同保存
                    FlowNode sameActivityImpl2 = (FlowNode) bpmnModel.getMainProcess().getFlowElement(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {// 有不相同跳出循环
                    break;
                }
            }
            List<SequenceFlow> pvmTransitions = activityImpl.getOutgoingFlows(); // 取出节点的所有出去的线
            for (SequenceFlow pvmTransition : pvmTransitions) {// 对所有的线进行遍历
                FlowNode pvmActivityImpl = (FlowNode) bpmnModel.getMainProcess().getFlowElement(pvmTransition.getTargetRef());// 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }
}
