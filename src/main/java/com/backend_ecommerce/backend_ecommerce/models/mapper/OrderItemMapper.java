package com.backend_ecommerce.backend_ecommerce.models.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.backend_ecommerce.backend_ecommerce.interfaces.order.MapperOrderItem;
import com.backend_ecommerce.backend_ecommerce.models.entity.OrderItem;
import com.backend_ecommerce.backend_ecommerce.models.entity.Orders;
import com.backend_ecommerce.backend_ecommerce.models.request.OrderItemRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderItemPageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderItemResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.services.order.CalculateTotalPriceOfOrder;
import com.backend_ecommerce.backend_ecommerce.services.shared.CalculateAmountToPayPerItem;

@Component
public class OrderItemMapper implements MapperOrderItem {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CalculateAmountToPayPerItem calculateAmountToPayPerItem;

    @Autowired
    private CalculateTotalPriceOfOrder calculateTotalPriceOfOrder;

    @Override
    public OrderItem toEntity(OrderItemRequest request) {
        return modelMapper.map(request, OrderItem.class);
    }

    @Override
    public OrderItem toEntity(Long id, OrderItemRequest request) {
        OrderItem orderItem = this.toEntity(request);
        orderItem.setId(id);

        return orderItem;
    }

    @Override
    public Orders toOrderEntity(Long OrderId) {
        Orders order = new Orders();
        order.setId(OrderId);

        return order;
    }

    @Override
    public OrderItemResponse toResponse(OrderItem entity) {
        OrderItemResponse orderItemResponse = modelMapper.map(entity, OrderItemResponse.class);
        orderItemResponse.setOrder(orderMapper.toResponse(entity.getOrder()));
        orderItemResponse.setProduct(productMapper.toResponse(entity.getProduct()));
        
        String amountToPay = calculateAmountToPayPerItem.execute(
            entity.getQuantity(), 
            entity.getProduct().getPrice()
        );

        orderItemResponse.setTotalPrice(amountToPay);

        return orderItemResponse;
    }

    @Override
    public List<OrderItemResponse> toResponseList(List<OrderItem> entities) {
        return entities.stream().map(entity -> this.toResponse(entity)).toList();
    }

    @Override
    public PageResponse<OrderItemResponse> toResponsePage(Page<OrderItem> entities) {
        PageResponse<OrderItemResponse> pageResponse = new PageResponse<>();

        pageResponse.setContent(this.toResponseList(entities.getContent()));
        pageResponse.setTotalPages(entities.getTotalPages());

        return pageResponse;
    }

    @Override
    public OrderItemPageResponse toResponsePage(List<OrderItem> entities,
        Page<OrderItem> orderItems) {

        OrderItemPageResponse orderItemPageResponse = new OrderItemPageResponse();
        PageResponse<OrderItemResponse> pageResponse = toResponsePage(orderItems);

        orderItemPageResponse.setContent(pageResponse.getContent());
        orderItemPageResponse.setTotalPages(pageResponse.getTotalPages());
        orderItemPageResponse.setTotalItems(orderItems.getTotalElements());

        String totalPrice = calculateTotalPriceOfOrder.execute(entities);

        orderItemPageResponse.setTotalPrice(totalPrice);

        return orderItemPageResponse;
    }
}
