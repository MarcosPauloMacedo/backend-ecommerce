package com.backend_ecommerce.backend_ecommerce.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend_ecommerce.backend_ecommerce.models.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}