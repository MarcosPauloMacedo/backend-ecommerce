package com.backend_ecommerce.backend_ecommerce.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend_ecommerce.backend_ecommerce.models.entity.OrderItem;
import com.backend_ecommerce.backend_ecommerce.models.entity.Orders;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Page<OrderItem> findByOrder(Orders order, Pageable pageable);
    List<OrderItem> findByOrder(Orders order);
}
