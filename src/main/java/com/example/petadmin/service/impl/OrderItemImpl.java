package com.example.petadmin.service.impl;

import com.example.petadmin.model.OrderItem;
import com.example.petadmin.model.Orders;
import com.example.petadmin.repository.OrderItemRepository;
import com.example.petadmin.repository.OrderRepository;
import com.example.petadmin.repository.ProductTypeRepository;
import com.example.petadmin.service.OrderItemService;
import com.example.petadmin.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderItemImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;



    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem getOrderItem(Long id) {
        return null;
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public Optional<OrderItem> findOne(Long id) {

        return orderItemRepository.findById(id);
    }


    @Override
    public void delete(Long id)
    {
        orderItemRepository.deleteById(id);
    }
    }

