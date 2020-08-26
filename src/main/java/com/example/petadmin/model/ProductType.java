package com.example.petadmin.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity

@Data
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Không được bỏ trống tên loại sản phẩm")
    @Size(min = 2, max = 50, message = "Độ dài phải từ 2 đến 14")
    private String name;
    @OneToMany(mappedBy = "productType")
    private Collection<Product> products;
}
