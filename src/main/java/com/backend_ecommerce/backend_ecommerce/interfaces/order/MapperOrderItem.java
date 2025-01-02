package com.backend_ecommerce.backend_ecommerce.interfaces.order;

import java.util.List;

import org.springframework.data.domain.Page;

import com.backend_ecommerce.backend_ecommerce.interfaces.shared.Mapper;
import com.backend_ecommerce.backend_ecommerce.models.entity.OrderItem;
import com.backend_ecommerce.backend_ecommerce.models.entity.Orders;
import com.backend_ecommerce.backend_ecommerce.models.request.OrderItemRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderItemPageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderItemResponse;

public interface MapperOrderItem extends Mapper<OrderItemRequest, OrderItemResponse, 
OrderItem> {
    Orders toOrderEntity(Long OrderId);
    OrderItemPageResponse toResponsePage(List<OrderItem> entities,
    Page<OrderItem> orderItems);
}
