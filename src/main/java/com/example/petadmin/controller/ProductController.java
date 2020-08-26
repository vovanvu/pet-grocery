package com.example.petadmin.controller;

import com.example.petadmin.model.Producer;
import com.example.petadmin.model.Product;
import com.example.petadmin.model.ProductType;
import com.example.petadmin.service.ProducerService;
import com.example.petadmin.service.ProductService;
import com.example.petadmin.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProducerService producerService;
    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/all")
    public String product(Model model) {
        List<Product> productList = productService.getAllProduct();
        model.addAttribute("productList", productList);
        return "product/allproduct";
    }


    @GetMapping("/add")
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        List<ProductType> productTypeList = productTypeService.getAllProductTypes();
        List<Producer> producerList = producerService.getAllProducers();
        model.addAttribute("listproductType", productTypeList);
        model.addAttribute("listproducer", producerList);

        return "product/addproduct";
    }

    @GetMapping("/edit")
    public String editProduct(@RequestParam("id") Long id, Model model) {
        Product product = productService.findOne(id).get();
        model.addAttribute("product", product);
        List<ProductType> productTypeList = productTypeService.getAllProductTypes();
        List<Producer> producerList = producerService.getAllProducers();
        model.addAttribute("listproductType", productTypeList);
        model.addAttribute("listproducer", producerList);

        return "/product/editproduct";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("id") Long id, Model model) {
        productService.findOne(id).ifPresent(product -> model.addAttribute("product", product));
        return "/product/deleteproduct";
    }

    @PostMapping("/add")
    public String addProductType(@Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product/addproduct";
        }
        LocalDateTime now = LocalDateTime.now();
        Date d = java.sql.Timestamp.valueOf(now);
        product.setAddedDate(d);
        productService.save(product);
        return "redirect:/admin/product/all";
    }

    @PostMapping("/edit")
    public String editProductType(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            return "/product/editproduct";
        }
        byte[] oldImage = productService.findOne(product.getId()).get().getImage();
        product.setImage(oldImage);
        model.addAttribute("product", product);
        LocalDateTime now = LocalDateTime.now();
        Date d = java.sql.Timestamp.valueOf(now);
        product.setAddedDate(d);
        productService.save(product);
        return "redirect:/admin/product/all";
    }

    @PostMapping("/delete")
    public String deleteProductType(@RequestParam("id") long id) {
        productService.delete(id);
        return "redirect:/admin/product/all";
    }
    @PostMapping("/upload")
    public @ResponseBody
    String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file, @RequestParam("idProduct") long id) {
        System.out.println("id gui len la :  "+id);
        try {
            // save file to PostgreSQL
            Product p1 = productService.findOne(id).get();
            p1.setImage(file.getBytes());
            productService.save(p1);

            return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
        } catch (Exception e) {
            return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
        }
    }
}
