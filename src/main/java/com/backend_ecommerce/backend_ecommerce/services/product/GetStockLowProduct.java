package com.backend_ecommerce.backend_ecommerce.services.product;

import com.backend_ecommerce.backend_ecommerce.models.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class GetStockLowProduct {

    public Specification<Product> execute(int quantity) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
            .lessThan(root.get("stock"), quantity);
    }
}