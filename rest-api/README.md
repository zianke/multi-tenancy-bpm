# activiti-rest-api

Run in terminal:

```
mvn spring-boot:run
```

## Spring Boot

Activiti-Spring-Boot is used to auto-configure.

## Rest-API

### start


* `POST:` (tenantId, orderJson)
* `return:` no return
* `effects:` Customer submit an order and start a process


### pending


* `GET:` (userId)
* `return:` JSONArray(taskId, orderJson)
* `effects:` List all the tasks that the user could adopt. It is filtered by tenant id and group id.


### claim


* `POST:` (taskId, userId)
* `return:` no return
* `effects:` Claim a task


### processing


* `GET:` (userId) </br>
* `return:` JSONArray(taskId, orderJson) </br>
* `effects:` List all the tasks that they user has adopted.


### task


* `GET:` (taskId) </br>
* `return:` (taskId, orderJson) </br>
* `effects:` List the queried task.


### complete


* `POST:` (taskId, comment) </br>
* `rerturn:` no return </br>
* `effects:` Compelete a task.


### auth


* `GET:` (userId, password) </br>
* `rerturn:` no return </br>
* `effects:` Check whether a user exists and password and username match.


### tasks


* `GET:` (tenantId) </br>
* `return:` JSONArray(taskId, orderJson) </br>
* `effects:` List all the tasks belong to the tenant.


