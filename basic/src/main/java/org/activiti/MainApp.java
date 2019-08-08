package org.activiti;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Task;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // Create Activiti process engine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // Get Activiti services
        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();

        IdentityService identityService = processEngine.getIdentityService();

        // Create users
        User foo1 = identityService.newUser("foo1");
        foo1.setFirstName("foo_first");
        foo1.setLastName("foo_last");
        identityService.saveUser(foo1);
        User bar1 = identityService.newUser("bar1");
        bar1.setFirstName("bar_first");
        bar1.setLastName("bar_last");
        identityService.saveUser(bar1);

        // Create groups and memberships
        identityService.deleteGroup("customer-service");
        identityService.deleteGroup("engineer");
        Group customerService = identityService.newGroup("customer-service");
        identityService.saveGroup(customerService);
        Group engineer = identityService.newGroup("engineer");
        identityService.saveGroup(engineer);
        identityService.createMembership("foo1", "customer-service");
        identityService.createMembership("bar1", "engineer");

        // Create users for test
        identityService.deleteUser("admin");
        User admin = identityService.newUser("admin");
        admin.setPassword("admin");
        identityService.saveUser(admin);
        User user;
        identityService.deleteUser("tubatu_customer-service_1");
        user = identityService.newUser("tubatu_customer-service_1");
        user.setPassword("tubatu_customer-service_1");
        identityService.saveUser(user);
        identityService.createMembership("tubatu_customer-service_1", "customer-service");
        identityService.deleteUser("tubatu_customer-service_2");
        user = identityService.newUser("tubatu_customer-service_2");
        user.setPassword("tubatu_customer-service_2");
        identityService.saveUser(user);
        identityService.createMembership("tubatu_customer-service_2", "customer-service");
        identityService.deleteUser("tubatu_engineer_1");
        user = identityService.newUser("tubatu_engineer_1");
        user.setPassword("tubatu_engineer_1");
        identityService.saveUser(user);
        identityService.createMembership("tubatu_engineer_1", "engineer");
        identityService.deleteUser("tubatu_engineer_2");
        user = identityService.newUser("tubatu_engineer_2");
        user.setPassword("tubatu_engineer_2");
        identityService.saveUser(user);
        identityService.createMembership("tubatu_engineer_2", "engineer");
        identityService.deleteUser("skshu_customer-service_1");
        user = identityService.newUser("skshu_customer-service_1");
        user.setPassword("skshu_customer-service_1");
        identityService.saveUser(user);
        identityService.createMembership("skshu_customer-service_1", "customer-service");
        identityService.deleteUser("skshu_customer-service_2");
        user = identityService.newUser("skshu_customer-service_2");
        user.setPassword("skshu_customer-service_2");
        identityService.saveUser(user);
        identityService.createMembership("skshu_customer-service_2", "customer-service");
        identityService.deleteUser("skshu_engineer_1");
        user = identityService.newUser("skshu_engineer_1");
        user.setPassword("skshu_engineer_1");
        identityService.saveUser(user);
        identityService.createMembership("skshu_engineer_1", "engineer");
        identityService.deleteUser("skshu_engineer_2");
        user = identityService.newUser("skshu_engineer_2");
        user.setPassword("skshu_engineer_2");
        identityService.saveUser(user);
        identityService.createMembership("skshu_engineer_2", "engineer");

        // Query the group of a user
        Group fooGroup = identityService.createGroupQuery().groupMember("foo1").singleResult();
        Group barGroup = identityService.createGroupQuery().groupMember("bar1").singleResult();
        System.out.println("- Query group from user:");
        System.out.println("foo1's group is " + fooGroup.getId());
        System.out.println("bar1's group is " + barGroup.getId());
        System.out.println();

        /*
        // If wrapper has been implemented:

        // Create tenants and employments
        Tenant tubatu = identityService.newTenant("tubatu");
        identityService.saveTenant(tubatu);
        identityService.createEmployment("foo1", "tubatu");
        identityService.createEmployment("bar1", "tubatu");

        // Query the tenant of a user
        Tenant fooTenant = identityService.createTenantQuery().tenantMember("foo1").singleResult();
        Tenant carTenant = identityService.createTenantQuery().tenantMember("bar1").singleResult();
         */

        // Deploy the process definition
        repositoryService.createDeployment()
                .addClasspathResource("HouseRenovationProcess.bpmn20.xml")
                .tenantId("tubatu")
                .deploy();
        repositoryService.createDeployment()
                .addClasspathResource("HouseRenovationProcess.bpmn20.xml")
                .tenantId("skshu")
                .deploy();

        // Start a process instance
        String procId1 = runtimeService.startProcessInstanceByKeyAndTenantId("houseRenovation", "tubatu").getId();
        String procId2 = runtimeService.startProcessInstanceByKeyAndTenantId("houseRenovation", "skshu").getId();

        // Get the first task
        TaskService taskService = processEngine.getTaskService();

        // Test query without tenant
        List<Task> taskList = taskService.createTaskQuery().taskCandidateUser("foo1").list();
        System.out.println("- Get foo1's tasks without tenant query:");
        for (Task task : taskList) {
            System.out.println(task.getName());
        }
        System.out.println();

        // Test query with tenant
        taskList = taskService.createTaskQuery().taskTenantId("tubatu").taskCandidateUser("foo1").list();
        System.out.println("- Get foo1's tasks with tenant query:");
        for (Task task : taskList) {
            System.out.println(task.getName());
        }
        System.out.println();

        // Should be nothing because the pre-request task is not completed
        taskList = taskService.createTaskQuery().taskTenantId("tubatu").taskCandidateUser("bar1").list();
        System.out.println("- Get bar1's tasks before foo1 completes:");
        for (Task task : taskList) {
            System.out.println(task.getName());
        }
        System.out.println();

        // foo1 complete the task
        taskList = taskService.createTaskQuery().taskTenantId("tubatu").taskCandidateUser("foo1").list();
        System.out.println("- Let foo1 claim and complete tasks:");
        for (Task task : taskList) {
            System.out.println(task.getName());
            taskService.claim(task.getId(), "foo1");
            taskService.complete(task.getId());
        }
        System.out.println();

        // Now bar1 can see his task
        taskList = taskService.createTaskQuery().taskTenantId("tubatu").taskCandidateUser("bar1").list();
        System.out.println("- Get bar1's tasks after foo1 completes:");
        for (Task task : taskList) {
            System.out.println(task.getName());
        }
        System.out.println();

        // bar1 complete the task
        taskList = taskService.createTaskQuery().taskTenantId("tubatu").taskCandidateUser("bar1").list();
        System.out.println("- Let bar1 claim and complete tasks:");
        for (Task task : taskList) {
            System.out.println(task.getName());
            taskService.claim(task.getId(), "bar1");
            taskService.complete(task.getId());
        }
        System.out.println();

        // Verify that process 1 is actually finished
        HistoryService historyService = processEngine.getHistoryService();
        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(procId1).singleResult();
        System.out.println("- Process instance 1 end time: " + historicProcessInstance.getEndTime());
        System.out.println();

        // Tasks of skshu completed by temporary users
        taskList = taskService.createTaskQuery().taskTenantId("skshu").taskCandidateGroup("customer-service").list();
        System.out.println("- Complete skshu's tasks for customer-service:");
        for (Task task : taskList) {
            System.out.println(task.getName());
            taskService.claim(task.getId(), "foo2");
            taskService.complete(task.getId());
        }
        System.out.println();
        taskList = taskService.createTaskQuery().taskTenantId("skshu").taskCandidateGroup("engineer").list();
        System.out.println("- Complete skshu's tasks for engineer:");
        for (Task task : taskList) {
            System.out.println(task.getName());
            taskService.claim(task.getId(), "bar2");
            taskService.complete(task.getId());
        }
        System.out.println();

        // Verify that process 2 is actually finished
        historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(procId2).singleResult();
        System.out.println("Process instance 2 end time: " + historicProcessInstance.getEndTime());

        // Clear database
        identityService.deleteMembership("foo1", "customer-service");
        identityService.deleteMembership("bar1", "engineer");
        identityService.deleteUser("foo1");
        identityService.deleteUser("bar1");
    }
}
