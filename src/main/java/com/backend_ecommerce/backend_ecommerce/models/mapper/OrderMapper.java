package com.backend_ecommerce.backend_ecommerce.models.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.backend_ecommerce.backend_ecommerce.interfaces.order.MapperOrder;
import com.backend_ecommerce.backend_ecommerce.models.entity.Orders;
import com.backend_ecommerce.backend_ecommerce.models.entity.User;
import com.backend_ecommerce.backend_ecommerce.models.request.OrderRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.OrderStatus;

@Component
public class OrderMapper implements MapperOrder {

    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public Orders toEntity(OrderRequest request) {
        return modelMapper.map(request, Orders.class);
    }

    @Override
    public Orders toEntity(Long id, OrderRequest request) {
        Orders order = this.toEntity(request);
        order.setId(id);

        return order;
    }

    @Override
    public Orders toEntity(OrderResponse orderResponse) {
        return modelMapper.map(orderResponse, Orders.class);
    }

    @Override
    public Orders toEntityByUser(Long userId) {
        var createAt = LocalDateTime.now();

        User user = new User();
        user.setId(userId);

        Orders order = new Orders();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setCreateAt(createAt);

        return order;
    }

    @Override
    public OrderResponse toResponse(Orders entity) {
        return modelMapper.map(entity, OrderResponse.class);
    }

    @Override
    public List<OrderResponse> toResponseList(List<Orders> entities) {
        return entities.stream().map(this::toResponse).toList();
    }

    @Override
    public PageResponse<OrderResponse> toResponsePage(Page<Orders> page) {
        PageResponse<OrderResponse> pageResponse = new PageResponse<>();
        pageResponse.setContent(this.toResponseList(page.getContent()));
        pageResponse.setTotalPages(page.getTotalPages());

        return pageResponse;
    }

}
