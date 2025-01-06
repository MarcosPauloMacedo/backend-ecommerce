package com.backend_ecommerce.backend_ecommerce.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend_ecommerce.backend_ecommerce.models.entity.Orders;
import com.backend_ecommerce.backend_ecommerce.models.entity.User;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUser(User user);

    @Query("SELECT o.user.email FROM Orders o WHERE o.id = :id")
    String findEmailByOrderId(@Param("id") Long id);
}