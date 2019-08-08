# activiti-rest-api

Run in terminal:

```
mvn spring-boot:run
```

## Spring Boot

Activiti-Spring-Boot is used to auto-configure.

## Rest-API

### start

```
* POST: (tenantId, orderJson) </br>
* return: no return  </br>
* functions: User submit an order and start a process
```

### pending

```
* GET: (userId)  </br>
* return: (taskId, orderJson) </br>
* functions: List all the tasks that the user could adopt. It is filtered by tenant id and group id.
```

### claim

```
* POST: (taskId, userId) </br>
* return: no return </br> 
* functions: laim a task
```

### processing

```
* GET: (userId) </br>
* return: (taskId, orderJson) </br>
List all the tasks that they user has adopted.
```

### task

```
* GET: (taskId) </br>
* return: (taskId, orderJson) </br>
* List the queryed task.
```

### complete

```
* POST: (taskId, comment) </br>
* rerturn: no return </br>
Compelete a task.
```

### auth

```
* GET: (userId, password) </br>
* rerturn: no return </br>
* Check whether a user exists and password and username match.
```

### tasks

```
* GET: (tenantId) </br>
* return: (taskId, orderJson) </br>
* functions: List all the tasks belong to the tenant.
```

### all

```
* POST: no args </br>
* rerturn: no return </br>
* An API to test whether all the steps could be done in the same time.
```

