package com.example.petadmin.controller;

import com.example.petadmin.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Calendar;

@Controller
public class StatsController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/admin/stats")
    public String stats(Model model) {
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int beforeMonth = Calendar.getInstance().get(Calendar.MONTH);
        double currentMonthRevenue = ordersService.getRevenueOfSelectedMonth("2020", String.valueOf(currentMonth));
        double beforeMonthRevenue = ordersService.getRevenueOfSelectedMonth("2020", String.valueOf(beforeMonth));
        model.addAttribute("currentMonth",currentMonth+"/"+currentYear);
        model.addAttribute("beforeMonth",beforeMonth+"/"+currentYear);
        model.addAttribute("currentMonthRevenue",currentMonthRevenue);
        model.addAttribute("beforeMonthRevenue",beforeMonthRevenue);
        return "statistics/stats";
    }
}
