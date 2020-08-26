package com.example.petadmin.controller;

import com.example.petadmin.model.OrderItem;
import com.example.petadmin.model.Orders;
import com.example.petadmin.model.ProductType;
import com.example.petadmin.repository.OrderRepository;
import com.example.petadmin.service.OrderItemService;
import com.example.petadmin.service.OrdersService;
import com.example.petadmin.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.Order;
import java.util.List;

@Controller
@RequestMapping("/admin/orderitem")
public class OrderItemController {
    @Autowired
    private OrderRepository orderRepository;
    @GetMapping("/all")
    public String productType(Model model,@RequestParam("id") Long id) {

        Orders order= orderRepository.getOne(id) ;

        model.addAttribute("items", order.getOrderItems());
        model.addAttribute("orderitem",order);
        return "orderitem/allorderitem";
    }

}
