package com.example.petadmin.service;

import com.example.petadmin.model.ProductType;
import java.util.List;
import java.util.Optional;

public interface ProductTypeService {
    ProductType save(ProductType type);
    List<ProductType> getAllProductTypes();
    Optional<ProductType> findOne(Long id);
    void delete(Long id);
}
