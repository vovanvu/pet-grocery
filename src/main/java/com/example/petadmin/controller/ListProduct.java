package com.example.petadmin.controller;

import com.example.petadmin.model.Orders;
import com.example.petadmin.model.Producer;
import com.example.petadmin.model.Product;
import com.example.petadmin.repository.ProductRepository;
import com.example.petadmin.service.ProductService;
import com.example.petadmin.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/shop")
public class ListProduct {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductRepository productRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("")
    public String list() {

        return "/shop";
    }

    @GetMapping("/productList")
    public String productListPage(HttpServletRequest request, Model model) {
        List<Product> products = productService.getAllProduct();

        for (Product product : products) {
            byte[] encode = Base64.getEncoder().encode(product.getImage());
            product.setImage(encode);

        }
        int page = 0; //default page number is 0 (yes it is weird)
        int size = 3; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("productList", productRepository.findAll(PageRequest.of(page, size)));
        return "/shop";
    }

    @GetMapping("/next")
    public String next(@RequestParam("id") long id, Model model, HttpServletRequest request) {
        List<Product> products = productService.getAllProduct();

        for (Product product : products) {
            byte[] encode = Base64.getEncoder().encode(product.getImage());
            product.setImage(encode);

        }
        int page = (int) id; //default page number is 0 (yes it is weird)
        int size = 3; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("productList", productRepository.findAll(PageRequest.of(page - 1, size)));
        return "/shop";
    }

    @GetMapping(value = "/all1")
    public @ResponseBody
    List<Product> getAllBook() {

        return productService.getAllProduct();
    }

    @GetMapping(value = "/productdetail")
    public String showProductDetail(@RequestParam("id") long id,Model model) {

        Product product = productService.findOne(id).get();
        byte[] encode = Base64.getEncoder().encode(product.getImage());
        product.setImage(encode);
        model.addAttribute("product",product);
        return "product";


    }

    @GetMapping(value = "/search")
    public @ResponseBody
    List<Product> showProductBySurname(HttpServletRequest request) {
        String query = request.getParameter("name");
        System.out.println(query);
        return productService.listProductByName(query);

    }


}