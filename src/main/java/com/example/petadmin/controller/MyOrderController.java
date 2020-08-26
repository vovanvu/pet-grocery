package com.example.petadmin.controller;

import com.example.petadmin.model.Orders;
import com.example.petadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class MyOrderController {
    @Autowired
    private UserService userService;
    @GetMapping("/myorder")
    public String myOrder(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        Collection<Orders> orders = userService.findByUsername(username).getOrders();
        Collections.reverse((List<?>) orders);
        model.addAttribute("ordersList",orders);
        return "my-order";
    }

}
