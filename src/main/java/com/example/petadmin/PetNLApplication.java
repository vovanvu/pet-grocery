package com.example.petadmin;

import com.example.petadmin.model.User;
import com.example.petadmin.security.Role;
import com.example.petadmin.security.UserRole;
import com.example.petadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class PetNLApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PetNLApplication.class, args);
    }

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setName("vo van vu");
        user1.setUsername("admin");
        user1.setPassword("admin");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(2);
        role1.setName("ROLE_ADMIN");
        userRoles.add(new UserRole(user1, role1));
        userService.save(user1, userRoles);
    }
}
