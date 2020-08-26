package com.example.petadmin.controller;

import com.example.petadmin.model.User;
import com.example.petadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String orders(Model model) {
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList", userList);
        return "user/alluser";
    }
}
