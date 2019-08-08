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
User submit an order and start a process
```

### pending

```
List all the tasks that the user could adopt. It is filtered by tenant id and group id.
```

### claim

```
Claim a task
```

### processing

```
List all the tasks that they user has adopted.
```

### task

```
List the queryed task.
```

### complete

```
Compelete a task.
```

### auth

```
Check whether a user exists and password and username match.
```

### tasks

```
List all the tasks belong to the tenant.
```

### all

```
An API to test whether all the steps could be done in the same time.
```

