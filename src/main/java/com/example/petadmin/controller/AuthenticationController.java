package com.example.petadmin.controller;

import com.example.petadmin.model.User;
import com.example.petadmin.security.Role;
import com.example.petadmin.security.UserRole;
import com.example.petadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        } else if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("usernameNotAvailable","Tên đăng nhập đã tồn tại!");
            return "register";
        } else {
            //set role user
            Role role = new Role();
            role.setRoleId(1);
            role.setName("ROLE_USER");
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, role));
            //
            userService.save(user, userRoles);
            return "redirect:/login";
        }
    }

}
