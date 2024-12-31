package com.backend_ecommerce.backend_ecommerce.services.product;

import com.backend_ecommerce.backend_ecommerce.models.entity.Product;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageProductFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductSearch {

    public Specification<Product> execute(PageProductFilter pageProductFilter) {
        String name = pageProductFilter.getName();
        String category = pageProductFilter.getCategory();
        Double minPrice = pageProductFilter.getMinPrice();
        Double maxPrice = pageProductFilter.getMaxPrice();

        Specification<Product> specification = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            specification = specification.and(hasName(name));
        }

        if (category != null && !category.isEmpty()) {
            specification = specification.and(hasCategory(category));
        }

        if (minPrice != null && maxPrice != null) {
            specification = specification.and(hasPriceBetween(minPrice, maxPrice));
        }

        return specification;
    }

    private Specification<Product> hasName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
        .like(root.get("name"), "%" + name + "%");
    }

    private Specification<Product> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
        .equal(root.get("category"), category);
    }

    private Specification<Product> hasPriceBetween(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
        .between(root.get("price"), minPrice, maxPrice);
    }
}