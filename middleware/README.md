# activiti-middleware

1. Start process `POST` `/start`

	- Input: `tenantId`, `orderJson`

	```
    String procId1 = runtimeService.startProcessInstanceByKeyAndTenantId("houseRenovation", "tenant1").getId();
    ```

2. List pending tasks `GET` `/pending`

	- Input: `userId`

	```
	taskList = taskService.createTaskQuery().taskTenantId("tenant2").taskCandidateGroup("customer-service").list();
	```
	
3. Claim task `POST` `/claim`

	- Input: `taskId`, `userId`

	```
	taskService.claim(task.getId(), "foo1");
	```
	
4. List processing tasks `GET` `/processing`

	- Input: `userId`

	```
	taskList = taskService.createTaskQuery().taskAssignee("foo2").list();

	```

5. Complete task `POST` `/complete`

	- Input: `taskId`

	```
	taskService.complete(task.getId());
	```
