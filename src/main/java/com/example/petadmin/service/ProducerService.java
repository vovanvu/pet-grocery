package com.example.petadmin.service;

import com.example.petadmin.model.Producer;
import com.example.petadmin.model.Product;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProducerService {
    public List<Producer> getAllProducers();

    Producer save(Producer type);

    Optional<Producer> findOne(Long id);

    void delete(Long id);
}
