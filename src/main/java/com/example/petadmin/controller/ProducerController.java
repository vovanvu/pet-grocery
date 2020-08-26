package com.example.petadmin.controller;

import com.example.petadmin.model.Producer;
import com.example.petadmin.model.ProductType;
import com.example.petadmin.service.ProducerService;
import com.example.petadmin.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/producer")
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @GetMapping("/all")
    public String producer(Model model) {
        List<Producer> producerList = producerService.getAllProducers();
        model.addAttribute("producerList", producerList);
        return "producer/allproducer";
    }

    @GetMapping("/add")
    public String addProducer(Model model) {
        Producer producer = new Producer();
        model.addAttribute("producer", producer);
        return "producer/addproducer";
    }

    @GetMapping("/edit")
    public String editProducer(@RequestParam("id") Long id, Model model) {
        producerService.findOne(id).ifPresent(producer -> model.addAttribute("producer", producer));
        return "/producer/editproducer";
    }

    @GetMapping("/delete")
    public String deleteProducer(@RequestParam("id") Long id, Model model) {
        producerService.findOne(id).ifPresent(producer -> model.addAttribute("producer", producer));
        return "/producer/deleteproducer";
    }

    @PostMapping("/add")
    public String addProducer(@Valid @ModelAttribute("producer") Producer producer, BindingResult result) {
        if (result.hasErrors()) {
            return "/producer/addproducer";
        }
        producerService.save(producer);
        return "redirect:/admin/producer/all";
    }

    @PostMapping("/edit")
    public String editProducer(@Valid @ModelAttribute("producer") Producer producer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("producer", producer);
            return "/producer/editproducer";
        }
        producerService.save(producer);
        return "redirect:/admin/producer/all";
    }

    @PostMapping("/delete")
    public String deleteProductType(@RequestParam("id") long id) {
        producerService.delete(id);
        return "redirect:/admin/producer/all";
    }
}
