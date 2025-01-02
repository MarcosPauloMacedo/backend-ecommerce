package com.backend_ecommerce.backend_ecommerce.services.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import com.backend_ecommerce.backend_ecommerce.models.entity.Orders;
import com.backend_ecommerce.backend_ecommerce.models.mapper.OrderMapper;
import com.backend_ecommerce.backend_ecommerce.models.repository.OrderRepository;
import com.backend_ecommerce.backend_ecommerce.models.request.OrderRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;
import com.backend_ecommerce.backend_ecommerce.services.shared.CreatePageable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CreatePageable createPageable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        OrderRequest request = new OrderRequest();
        Orders order = new Orders();
        Orders savedOrder = new Orders();
        OrderResponse response = new OrderResponse();

        when(orderMapper.toEntity(request)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(savedOrder);
        when(orderMapper.toResponse(savedOrder)).thenReturn(response);

        OrderResponse result = orderService.save(request);

        assertEquals(response, result);
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        OrderRequest request = new OrderRequest();
        Orders order = new Orders();
        Orders updatedOrder = new Orders();
        OrderResponse response = new OrderResponse();

        when(orderMapper.toEntity(id, request)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(updatedOrder);
        when(orderMapper.toResponse(updatedOrder)).thenReturn(response);

        OrderResponse result = orderService.update(id, request);

        assertEquals(response, result);
    }

    @Test
    void testSelectById() {
        Long id = 1L;
        Orders order = new Orders();
        OrderResponse response = new OrderResponse();

        when(orderRepository.findById(id)).thenReturn(Optional.of(order));
        when(orderMapper.toResponse(order)).thenReturn(response);

        OrderResponse result = orderService.selectById(id);

        assertEquals(response, result);
    }

    @Test
    void testSelectAll() {
        PageFilter filter = new PageFilter();
        Pageable pageable = Pageable.unpaged();
        Page<Orders> orders = new PageImpl<>(Arrays.asList(new Orders()));
        PageResponse<OrderResponse> response = new PageResponse<>();

        when(createPageable.execute(filter)).thenReturn(pageable);
        when(orderRepository.findAll(pageable)).thenReturn(orders);
        when(orderMapper.toResponsePage(orders)).thenReturn(response);

        PageResponse<OrderResponse> result = orderService.selectAll(filter);

        assertEquals(response, result);
    }
}
