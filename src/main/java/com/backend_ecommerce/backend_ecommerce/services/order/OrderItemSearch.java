package com.backend_ecommerce.backend_ecommerce.services.order;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.models.entity.OrderItem;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageOrderItemsFilter;

@Service
public class OrderItemSearch {

    public Specification<OrderItem> execute(PageOrderItemsFilter pageOrderItemsFilter) {
        String category = pageOrderItemsFilter.getCategory();

        Specification<OrderItem> specification = Specification.where(null);

        if (category != null && !category.isEmpty()) {
            specification = specification.and(hasCategoryInProduct(category));
        }

        return specification;
    }

    private Specification<OrderItem> hasCategoryInProduct(String category) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.join("product").get("category"), category);
        };
    }
}
