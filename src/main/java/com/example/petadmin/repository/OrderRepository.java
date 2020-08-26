package com.example.petadmin.repository;

import com.example.petadmin.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Modifying
    @Query("update Orders o set o.status = ?1 where o.id = ?2")
    void updateStatus(int status, Long id);

    @Query(
            value = "SELECT * " +
                    "FROM orders o " +
                    "WHERE year (o.order_date) = :year and  month(o.order_date) = :month",
            nativeQuery = true)
    List<Orders> getALlOrdersOfSelectedMonth(String year, String month);

    @Query(
            value = "SELECT sum(total_price) " +
                    "FROM orders o " +
                    "WHERE year (o.order_date) = :year and  month(o.order_date) = :month " +
                    "GROUP by month(o.order_date)",
            nativeQuery = true)
    double getRevenueOfSelectedMonth(String year, String month);
}


