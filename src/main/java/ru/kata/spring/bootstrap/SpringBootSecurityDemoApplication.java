package ru.kata.spring.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.kata.spring.bootstrap.Service.RoleService;
import ru.kata.spring.bootstrap.Service.UserService;
import ru.kata.spring.bootstrap.models.Role;
import ru.kata.spring.bootstrap.models.User;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

    public static void main(String[] args) {
//		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
        ApplicationContext context = SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
        try {
            UserService userService = context.getBean(UserService.class);
            RoleService roleService = context.getBean(RoleService.class);

            User user1 = new User("admin", "admin", "admin", 20, "admin@mail.com");
            User user2 = new User("Pavel", "Pavlov", "Pavel", 30, "Pavel@mail.com");
            User user3 = new User("Ivan", "Ivanov", "Ivan", 40, "Ivan@mail.com");

            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");

            Set<Role> rolesAdmin = new HashSet<>();
            rolesAdmin.add(roleAdmin);
            rolesAdmin.add(roleUser);

            Set<Role> rolesUsers = new HashSet<>();
            rolesUsers.add(roleUser);

            user1.setRoles(rolesAdmin);
            user2.setRoles(rolesUsers);
            user3.setRoles(rolesUsers);

            roleService.addRole(roleAdmin);
            roleService.addRole(roleUser);

            userService.saveUser(user1);
            userService.saveUser(user2);
            userService.saveUser(user3);

        } catch (Exception ignored) {
        }
    }
}
