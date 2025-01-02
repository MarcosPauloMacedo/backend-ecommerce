package com.backend_ecommerce.backend_ecommerce.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend_ecommerce.backend_ecommerce.models.entity.Orders;
import com.backend_ecommerce.backend_ecommerce.models.entity.User;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUser(User user);
}
