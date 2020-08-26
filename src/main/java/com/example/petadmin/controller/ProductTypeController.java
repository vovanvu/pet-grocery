package com.example.petadmin.controller;

import com.example.petadmin.model.ProductType;
import com.example.petadmin.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/producttype")
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/all")
    public String productType(Model model) {
        List<ProductType> productTypeList = productTypeService.getAllProductTypes();
        model.addAttribute("productTypeList", productTypeList);
        return "producttype/allproducttype";
    }

    @GetMapping("/add")
    public String addProductType(Model model) {
        ProductType type = new ProductType();
        model.addAttribute("type", type);
        return "producttype/addproducttype";
    }


    @GetMapping("/edit")
    public String editProductType(@RequestParam("id") Long id, Model model) {
        productTypeService.findOne(id).ifPresent(type -> model.addAttribute("type", type));
        return "/producttype/editproducttype";
    }

    @GetMapping("/delete")
    public String deleteProductType(@RequestParam("id") Long id, Model model) {
        productTypeService.findOne(id).ifPresent(type -> model.addAttribute("type", type));
        return "/producttype/deleteproducttype";
    }

    @PostMapping("/add")
    public String addProductType(@Valid @ModelAttribute("type") ProductType productType, BindingResult result) {
        if (result.hasErrors()) {
            return "producttype/addproducttype";
        }
        productTypeService.save(productType);
        return "redirect:/admin/producttype/all";
    }

    @PostMapping("/edit")
    public String editProductType(@Valid @ModelAttribute("type") ProductType productType, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("type", productType);
            return "/producttype/editproducttype";
        }
        productTypeService.save(productType);
        return "redirect:/admin/producttype/all";
    }

    @PostMapping("/delete")
    public String deleteProductType(@RequestParam("id") long id) {
        productTypeService.delete(id);
        return "redirect:/admin/producttype/all";
    }
}
