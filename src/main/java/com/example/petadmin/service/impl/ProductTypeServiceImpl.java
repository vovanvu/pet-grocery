package com.example.petadmin.service.impl;

import com.example.petadmin.model.ProductType;
import com.example.petadmin.repository.ProductTypeRepository;
import com.example.petadmin.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public ProductType save(ProductType type) {
        return productTypeRepository.save(type);
    }

    @Override
    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    @Override
    public Optional<ProductType> findOne(Long id) {
        return productTypeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        productTypeRepository.deleteById(id);
    }


}
