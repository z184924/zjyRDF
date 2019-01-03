package cn.zhangjingyao.controller.api.processs;

import cn.zhangjingyao.controller.base.BaseController;
import cn.zhangjingyao.entity.PageData;
import cn.zhangjingyao.entity.system.User;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/testProcess")
public class TestProcessController extends BaseController {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private IdentityService identityService;


    @ResponseBody
    @RequestMapping(value = "/queryMyTask", produces = "application/json;charset=UTF-8")
    public String queryMyTask() throws Exception {
        logBefore(logger, "查询全部任务");
        User currentUser = this.getCurrentUser();
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(currentUser.getAccount()).list();
        PageData resultMap=new PageData();
        resultMap.put("taskList",taskList);
        return this.jsonContent("success",resultMap);
    }

    @ResponseBody
    @RequestMapping(value = "/start", produces = "application/json;charset=UTF-8")
    public String start() throws Exception {
        logBefore(logger, "启动流程testProcess");
        runtimeService.startProcessInstanceByKey("myProcess_1");
        return this.jsonContent("success", "启动成功");
    }

    @ResponseBody
    @RequestMapping(value = "/finishTask1", produces = "application/json;charset=UTF-8")
    public String finishTask1() throws Exception {
        logBefore(logger, "完成Task1");
        taskService.claim("2505", "");
        return this.jsonContent("success", "完成Task1");
    }
}
