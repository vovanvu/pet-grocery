package com.example.petadmin.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "orders",fetch = FetchType.EAGER,cascade=CascadeType.PERSIST)
    private Collection<OrderItem> orderItems;
    @NotBlank(message = "Không được bỏ trống tên sản phẩm")
    @Size(min = 2, max = 50, message = "Độ dài phải từ 2 đến 50")
    private String orderName;
    private Date orderDate;
    @NotBlank(message = "Không được bỏ trống địa chỉ")
    @Size(min = 10, max = 100, message = "Độ dài phải từ 10 đến 100")
    private String address;
    @NotBlank(message = "Không được bỏ trống số điện thoại")
    @Size(min = 10, max = 10, message = "Vui lòng nhập đúng độ dài số điện thoại")
    @Pattern(regexp = "(\\+84|0)[0-9]{9}", message = "Vui lòng nhập đúng số điện thoại")
    private String phoneNumber;
    private int status;
    private double totalPrice;
}
