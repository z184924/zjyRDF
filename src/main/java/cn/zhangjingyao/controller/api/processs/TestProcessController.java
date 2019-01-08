package cn.zhangjingyao.controller.api.processs;

import cn.zhangjingyao.controller.base.BaseController;
import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.entity.system.User;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
            resultList.add(task);
        }
        PageData resultMap=new PageData();
        resultMap.put("taskList",resultList);
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
}
