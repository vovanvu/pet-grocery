package com.example.petadmin.model;


import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemId implements Serializable {
    private Orders orders;
    private Product product;
}
