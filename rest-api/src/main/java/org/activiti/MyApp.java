package org.activiti;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class MyApp {

    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }

    @Bean
    public DataSource database() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://127.0.0.1:3306/activiti?characterEncoding=UTF-8")
                .username("activiti")
                .password("activiti")
                .driverClassName("com.mysql.jdbc.Driver")
                .build();
    }

    /*
    @Bean
    InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {

        return new InitializingBean() {
            public void afterPropertiesSet() throws Exception {

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
                Group customerService = identityService.newGroup("customer-service");
                identityService.saveGroup(customerService);
                Group engineer = identityService.newGroup("engineer");
                identityService.saveGroup(engineer);
                identityService.createMembership("foo1", "customer-service");
                identityService.createMembership("bar1", "engineer");

            }
        };
    }
    */

}
