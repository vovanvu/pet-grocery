package com.example.petadmin.service;

import com.example.petadmin.model.Orders;
import com.example.petadmin.model.ProductType;

import java.util.List;
import java.util.Optional;

public interface OrdersService {
    public List<Orders> getAllOrders();

    public Orders getOrders(Long id);

    Orders save(Orders orders);

    Optional<Orders> findOne(Long id);

    void delete(Long id);

    void updateStatus(int status, Long id);

    List<Orders> getAllOfSelectedMonth(String year, String month);
    double getRevenueOfSelectedMonth(String year, String month);
}
