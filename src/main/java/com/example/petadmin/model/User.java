package com.example.petadmin.model;


import com.example.petadmin.security.Authority;
import com.example.petadmin.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity

@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Không được bỏ trống tên")
    @Size(min = 4, max = 20, message = "Độ dài phải từ 4 đến 50")
    private String name;
    @NotBlank(message = "Không được bỏ trống mật khẩu")
    @Size(min = 6, max = 500, message = "Độ dài phải nhiều hơn 6 kí tự")
    private String password;
    @NotBlank(message = "Không được bỏ trống tên đăng nhập")
    @Size(min = 4, max = 20, message = "Độ dài phải từ 4 đến 20")
    private String username;
    @OneToMany(mappedBy = "user")
    private Collection<Orders> orders;
    //security
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserRole> userRole = new HashSet<>();

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        userRole.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));
        return authorities;
    }
}
