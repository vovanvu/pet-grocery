package com.example.petadmin.repository;

import com.example.petadmin.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findTop10ByOrderByAddedDateDesc();
    @Query( value = "SELECT * FROM product t WHERE t.name like %?1% ",nativeQuery = true)
    List<Product> findByName(String surname);

    @Modifying
    @Query("update Product o set o.image = ?1 where o.id = ?2")
    void updateImage(byte[] image, Long id);
}