package com.backend_ecommerce.backend_ecommerce.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend_ecommerce.backend_ecommerce.models.entity.Cart;
import java.util.List;
import com.backend_ecommerce.backend_ecommerce.models.entity.User;


public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);
}
