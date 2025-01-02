package com.backend_ecommerce.backend_ecommerce.services.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.interfaces.order.DataServiceOrderItem;
import com.backend_ecommerce.backend_ecommerce.models.entity.OrderItem;
import com.backend_ecommerce.backend_ecommerce.models.entity.Orders;
import com.backend_ecommerce.backend_ecommerce.models.mapper.OrderItemMapper;
import com.backend_ecommerce.backend_ecommerce.models.repository.OrderItemRepository;
import com.backend_ecommerce.backend_ecommerce.models.request.OrderItemRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderItemPageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderItemResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;
import com.backend_ecommerce.backend_ecommerce.services.shared.CreatePageable;

@Service
public class OrderItemService implements DataServiceOrderItem {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CreatePageable createPageable;
    
    @Override
    public OrderItemResponse save(OrderItemRequest request) {
        OrderItem orderItem = orderItemMapper.toEntity(request);
        OrderItem orderItemSave = orderItemRepository.save(orderItem);

        return orderItemMapper.toResponse(orderItemSave);
    }

    @Override
    public OrderItemResponse update(Long id, OrderItemRequest request) {
        OrderItem orderItem = orderItemMapper.toEntity(id, request);
        OrderItem orderItemUpdate = orderItemRepository.save(orderItem);

        return orderItemMapper.toResponse(orderItemUpdate);
    }

    @Override
    public OrderItemResponse selectById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id).get();
        return orderItemMapper.toResponse(orderItem);
    }

    @Override
    public PageResponse<OrderItemResponse> selectAll(PageFilter filter) {
        Pageable pageable = createPageable.execute(filter);

        Page<OrderItem> orderItems = orderItemRepository.findAll(pageable);

        return orderItemMapper.toResponsePage(orderItems);
    }

    @Override
    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItemPageResponse selectAllByOrderId(Long orderId, PageFilter filter) {
        Pageable pageable = createPageable.execute(filter);

        Orders order = orderItemMapper.toOrderEntity(orderId);
        
        List<OrderItem> ordersAll = orderItemRepository.findByOrder(order);
        Page<OrderItem> orderItems = orderItemRepository.findByOrder(order, pageable);

        return orderItemMapper.toResponsePage(ordersAll, orderItems);
    }
}
