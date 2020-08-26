package com.example.petadmin.controller;

import com.example.petadmin.model.Product;
import com.example.petadmin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Base64;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        List<Product> list = productService.findLast10AddedDate();
        for (Product product : list) {
            byte[] encode = Base64.getEncoder().encode(product.getImage());
            product.setImage(encode);
        }
        model.addAttribute("list",list);
        return "index";
    }
}
