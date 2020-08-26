package com.example.petadmin.service.impl;

import com.example.petadmin.model.Producer;
import com.example.petadmin.repository.ProducerRepository;
import com.example.petadmin.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private ProducerRepository producerRepository;

    @Override
    public List<Producer> getAllProducers() {
        return producerRepository.findAll();
    }

    @Override
    public Producer save(Producer type) {
        return producerRepository.save(type);
    }

    @Override
    public Optional<Producer> findOne(Long id) {
        return producerRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        producerRepository.deleteById(id);
    }
}
