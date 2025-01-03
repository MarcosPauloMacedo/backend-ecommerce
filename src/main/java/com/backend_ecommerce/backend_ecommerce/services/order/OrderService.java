package com.backend_ecommerce.backend_ecommerce.services.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.interfaces.order.DataServiceOrder;
import com.backend_ecommerce.backend_ecommerce.models.entity.Orders;
import com.backend_ecommerce.backend_ecommerce.models.mapper.OrderMapper;
import com.backend_ecommerce.backend_ecommerce.models.repository.OrderRepository;
import com.backend_ecommerce.backend_ecommerce.models.request.OrderRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;
import com.backend_ecommerce.backend_ecommerce.services.shared.CreatePageable;
import com.backend_ecommerce.backend_ecommerce.services.shared.ValidateIfExistsById;

@Service
public class OrderService implements DataServiceOrder {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CreatePageable createPageable;

    @Autowired
    private ValidateIfExistsById validateIfExistsById;


    @Override
    public OrderResponse save(OrderRequest request) {
        Orders order = orderMapper.toEntity(request);
        Orders orderSave = orderRepository.save(order);

        return orderMapper.toResponse(orderSave);
    }

    @Override
    public OrderResponse update(Long id, OrderRequest request) {
        validateIfExistsById.inOrder(id);
        
        Orders order = orderMapper.toEntity(id, request);
        Orders orderUpdate = orderRepository.save(order);

        return orderMapper.toResponse(orderUpdate);
    }

    @Override
    public OrderResponse selectById(Long id) {
        validateIfExistsById.inOrder(id);

        Orders order = orderRepository.findById(id).get();

        return orderMapper.toResponse(order);
    }

    @Override
    public PageResponse<OrderResponse> selectAll(PageFilter filter) {
        Pageable pageable = createPageable.execute(filter);

        Page<Orders> orders = orderRepository.findAll(pageable);

        return orderMapper.toResponsePage(orders);
    }

    @Override
    public void delete(Long id) {
        validateIfExistsById.inOrder(id);
        orderRepository.deleteById(id);
    }
}
