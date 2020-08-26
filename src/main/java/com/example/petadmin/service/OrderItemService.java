package com.example.petadmin.service;

import com.example.petadmin.model.OrderItem;
import com.example.petadmin.model.Orders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    public List<OrderItem> getAllOrderItems();
    public OrderItem getOrderItem(Long id);
    OrderItem save(OrderItem orderItem);

    Optional<OrderItem> findOne(Long id);
    void delete(Long id);
}
