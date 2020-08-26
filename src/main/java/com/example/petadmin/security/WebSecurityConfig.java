package com.example.petadmin.security;

import com.example.petadmin.service.impl.UserSecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserSecurityServiceImpl userSecurityService;

    private static final String[] PUBLIC_MACTCHES = {
            "/petuser/**",
            "/",
            "/buy",
            "/buyshop",
            "/listofproducts",
            "/cart",
            "/update-cart",
            "remove-product",
            "/login",
            "/register",
            "/shop/productList",
            "/shop/search",
            "/shop/productdetail",
            "/shop/next"

    };



    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userSecurityService) // Cung cáp userservice cho spring security
                .passwordEncoder(passwordEncoder()); // cung cấp password encoder
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(PUBLIC_MACTCHES).permitAll() // Cho phép tất cả mọi người truy cập vào các địa chỉ này
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated(); // Tất cả các request khác đều cần phải xác thực mới được truy cập

        http
                .csrf().disable()
                .formLogin().loginPage("/login") // Cho phép người dùng xác thực bằng form login
                .permitAll()
                .defaultSuccessUrl("/",true)// Tất cả đều được truy cập vào địa chỉ này
                .and()
                .logout() // Cho phép logout
                .permitAll();
    }
}