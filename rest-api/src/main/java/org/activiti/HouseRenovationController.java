package org.activiti;


import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HouseRenovationController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private TaskService taskService;

    @CrossOrigin
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public void start(@RequestBody Map<String, String> data) {
        String tenantId = data.get("tenantId");
        String orderJson = data.get("orderJson");
        Map<String, Object> var = new HashMap<>();
        var.put("orderJson", orderJson);
        String procId = runtimeService.startProcessInstanceByKeyAndTenantId("houseRenovation", var, tenantId).getId();
        System.out.println(procId);
    }

    @CrossOrigin
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/pending", method = RequestMethod.GET)
    public void pending(@RequestBody Map<String, String> data, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String userId = data.get("userId");
        String[] strs = userId.split("_");
        String tenantId = strs[0];
        String group = identityService.createGroupQuery().groupMember(userId).singleResult().getId();
        JSONArray tasksJson = new JSONArray();
        List<Task> tasks = taskService.createTaskQuery().taskTenantId(tenantId).taskCandidateGroup(group).list();
        for (Task task : tasks) {
            JSONObject taskJson = new JSONObject();
            taskJson.put("taskId", task.getId());
            Map<String, Object> var = taskService.getVariables(task.getId());
            taskJson.put("orderJson", var.get("orderJson"));
            tasksJson.put(taskJson);
        }
        out.println(tasksJson);
    }

    @CrossOrigin
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/claim", method = RequestMethod.POST)
    public void claim(@RequestBody Map<String, String> data) throws IOException {
        String taskId = data.get("taskId");
        String userId = data.get("userId");
        System.out.println("Claim taskId: " + taskId + ", userId: " + userId);
        taskService.claim(taskId, userId);
    }

    @CrossOrigin
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/processing", method = RequestMethod.GET)
    public void processing(@RequestBody Map<String, String> data, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String userId = data.get("userId");
        JSONArray tasksJson = new JSONArray();
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).list();
        for (Task task : tasks) {
            JSONObject taskJson = new JSONObject();
            taskJson.put("taskId", task.getId());
            Map<String, Object> var = taskService.getVariables(task.getId());
            taskJson.put("orderJson", var.get("orderJson"));
            tasksJson.put(taskJson);
        }
        out.println(tasksJson);
    }

    @CrossOrigin
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/task", method = RequestMethod.GET)
    public void task(@RequestBody Map<String, String> data, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String taskId = data.get("taskId");
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        JSONObject taskJson = new JSONObject();
        if (task != null) {
            taskJson.put("taskId", task.getId());
            Map<String, Object> var = taskService.getVariables(task.getId());
            taskJson.put("orderJson", var.get("orderJson"));
        }
        out.println(taskJson);
    }

    @CrossOrigin
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    public void complete(@RequestBody Map<String, String> data) throws IOException {
        String taskId = data.get("taskId");
        String comment = data.get("comment");
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task != null) {
            Map<String, Object> var = taskService.getVariables(task.getId());
            String orderJson = (String) var.get("orderJson");
            JSONObject orderObject = new JSONObject(orderJson);
            orderObject.put("comment", comment);
            orderJson = orderObject.toString();
            var.put("orderJson", orderJson);
            taskService.setVariables(task.getId(), var);
        }
        System.out.println("Complete taskId: " + taskId);
        taskService.complete(taskId);
    }

    @CrossOrigin
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public void auth(@RequestBody Map<String, String> data, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String userId = data.get("userId");
        String password = data.get("password");
        out.println(identityService.checkPassword(userId, password));
    }


    @CrossOrigin
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public void tasks(@RequestBody Map<String, String> data, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String tenantId = data.get("tenantId");
        JSONArray tasksJson = new JSONArray();
        List<Task> tasks = taskService.createTaskQuery().taskTenantId(tenantId).list();
        for (Task task : tasks) {
            JSONObject taskJson = new JSONObject();
            taskJson.put("taskId", task.getId());
            Map<String, Object> var = taskService.getVariables(task.getId());
            taskJson.put("orderJson", var.get("orderJson"));
            tasksJson.put(taskJson);
        }
        out.println(tasksJson);
    }

    @CrossOrigin
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public void all(@RequestBody Map<String, String> data) {
        runtimeService.startProcessInstanceByKeyAndTenantId("houseRenovation", "tenant1");
        List<Task> taskList = taskService.createTaskQuery().taskCandidateUser("foo1").list();
        System.out.println("- Get foo1's tasks without tenant query:");
        for (Task task : taskList) {
            System.out.println(task.getName());
        }
        System.out.println();

        // foo1 complete the task
        taskList = taskService.createTaskQuery().taskTenantId("tenant1").taskCandidateUser("foo1").list();
        System.out.println("- Let foo1 claim and complete tasks:");
        for (Task task : taskList) {
            System.out.println(task.getName());
            taskService.claim(task.getId(), "foo1");
            taskService.complete(task.getId());
        }
        System.out.println();

        // Now bar1 can see his task
        taskList = taskService.createTaskQuery().taskTenantId("tenant1").taskCandidateUser("bar1").list();
        System.out.println("- Get bar1's tasks after foo1 completes:");
        for (Task task : taskList) {
            System.out.println(task.getName());
        }
        System.out.println();

        // bar1 complete the task
        taskList = taskService.createTaskQuery().taskTenantId("tenant1").taskCandidateUser("bar1").list();
        System.out.println("- Let bar1 claim and complete tasks:");
        for (Task task : taskList) {
            System.out.println(task.getName());
            taskService.claim(task.getId(), "bar1");
            taskService.complete(task.getId());
        }
        System.out.println();
    }

}
