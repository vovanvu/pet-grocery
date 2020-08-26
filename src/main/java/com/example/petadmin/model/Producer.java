package com.example.petadmin.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity

@Data
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Không được bỏ trống tên sản phẩm")
    @Size(min = 2, max = 50, message = "Độ dài phải từ 2 đến 14")
    private String nameProducer;
    private String address;
    private String phoneNumber;
    private String email;
    @OneToMany(mappedBy = "producer",fetch = FetchType.EAGER)
    private Collection<Product> products;
}
