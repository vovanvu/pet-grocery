package com.example.petadmin.service.impl;

import com.example.petadmin.model.Product;
import com.example.petadmin.model.ProductType;
import com.example.petadmin.repository.ProductRepository;
import com.example.petadmin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product type) {
        return productRepository.save(type);
    }

    @Override
    public Optional<Product> findOne(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public List<Product> findLast10AddedDate() {
        return productRepository.findTop10ByOrderByAddedDateDesc();
    }
    @Override
    public List<Product> listProductByName(String surname) {
        return productRepository.findByName(surname);
    }

    @Override
    public void updateImage(byte[] image, Long id) {
        productRepository.updateImage(image,id);
    }


}
