package com.example.petadmin.service.impl;

import com.example.petadmin.model.Orders;
import com.example.petadmin.repository.OrderRepository;
import com.example.petadmin.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Orders getOrders(Long id) {
        return null;
    }

    @Override
    public Orders save(Orders orders) {
        return orderRepository.save(orders);
    }

    @Override
    public Optional<Orders> findOne(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void updateStatus(int status, Long id) {
        orderRepository.updateStatus(status,id);
    }

    @Override
    public List<Orders> getAllOfSelectedMonth(String year, String month) {
        return orderRepository.getALlOrdersOfSelectedMonth(year, month);
    }

    @Override
    public double getRevenueOfSelectedMonth(String year, String month) {
        return orderRepository.getRevenueOfSelectedMonth(year, month);
    }

}
