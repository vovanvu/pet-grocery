package com.example.petadmin.service;

import com.example.petadmin.model.Product;
import com.example.petadmin.model.ProductType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> getAllProduct();

    Product save(Product type);

    Optional<Product> findOne(Long id);

    void delete(Long id);

    List<Product> findLast10AddedDate();
    List<Product> listProductByName(String surname);
    void updateImage(byte[] image, Long id);
}
