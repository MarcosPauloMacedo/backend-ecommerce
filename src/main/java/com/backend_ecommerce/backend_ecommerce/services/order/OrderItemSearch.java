package com.backend_ecommerce.backend_ecommerce.services.order;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.models.entity.OrderItem;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageOrderItemsFilter;

@Service
public class OrderItemSearch {

    public Specification<OrderItem> execute(PageOrderItemsFilter pageOrderItemsFilter) {
        String category = pageOrderItemsFilter.getCategory();
        LocalDateTime startDateTime = pageOrderItemsFilter.getStartDateTime();
        LocalDateTime endDateTime = pageOrderItemsFilter.getEndDateTime();

        Specification<OrderItem> specification = Specification.where(null);

        if (category != null && !category.isEmpty()) {
            specification = specification.and(hasCategoryInProduct(category));
        }

        if (startDateTime != null || endDateTime != null) {
            specification = specification.and(hasLocalDateTime(startDateTime, endDateTime));
        }

        return specification;
    }

    private Specification<OrderItem> hasCategoryInProduct(String category) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.join("product")
            .get("category"), category);
        };
    }

    private Specification<OrderItem> hasLocalDateTime(LocalDateTime startDateTime, 
    LocalDateTime endDateTime) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.between(root.join("order")
            .get("createAt"), startDateTime, endDateTime);
        };
    }
}
