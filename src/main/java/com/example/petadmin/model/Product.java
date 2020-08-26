package com.example.petadmin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity

@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Không được bỏ trống tên sản phẩm")
    @Size(min = 2, max = 50, message = "Độ dài phải từ 2 đến 50")
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_type_id")
    @JsonIgnore
    private ProductType productType;
    private double price;
    @ManyToOne
    @JoinColumn(name = "producer_id")
    @JsonIgnore
    private Producer producer;
    private int stock;
    @Lob
    private byte[] image;
    private String description;
    private Date addedDate;
    public String getImageEncoded() {
        return new String(this.image);
    }
}
