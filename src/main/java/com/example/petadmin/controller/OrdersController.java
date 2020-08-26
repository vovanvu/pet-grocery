package com.example.petadmin.controller;

import com.example.petadmin.model.Orders;
import com.example.petadmin.model.ProductType;
import com.example.petadmin.service.OrdersService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class OrdersController {
    @Autowired
    private OrdersService  ordersService;

    @GetMapping("/all")
    public String orders(Model model) {
        List<Orders> ordersList = ordersService.getAllOrders();
        model.addAttribute("ordersList", ordersList);
        return "orders/allorders";
    }


    @GetMapping("/edit")
    public String editProductType(@RequestParam("id") Long id, Model model) {
        ordersService.findOne(id).ifPresent(orders -> model.addAttribute("orders", orders));

        return "/orders/editorders";
    }

    @PostMapping("/edit")
    public String editProductType( @ModelAttribute("orders") Orders orders, BindingResult result, Model model) {

        ordersService.updateStatus(orders.getStatus(),orders.getId());

        return "redirect:/admin/orders/all";
    }

}
