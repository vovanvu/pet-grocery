package com.example.petadmin.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(OrderItemId.class)
public class OrderItem {
    @Id
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    private double price;
    private double subtotal;
    public OrderItem(Orders order, Product product){
        this.orders = order;
        this.product = product;
    }
}