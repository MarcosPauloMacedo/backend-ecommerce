package com.backend_ecommerce.backend_ecommerce.services.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class OrderItemServiceTest {

    @InjectMocks
    private OrderItemService orderItemService;

    @Mock
    private OrderItemMapper orderItemMapper;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private CreatePageable createPageable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        OrderItemRequest request = new OrderItemRequest();
        OrderItem orderItem = new OrderItem();
        OrderItem savedOrderItem = new OrderItem();
        OrderItemResponse response = new OrderItemResponse();

        when(orderItemMapper.toEntity(request)).thenReturn(orderItem);
        when(orderItemRepository.save(orderItem)).thenReturn(savedOrderItem);
        when(orderItemMapper.toResponse(savedOrderItem)).thenReturn(response);

        OrderItemResponse result = orderItemService.save(request);

        assertEquals(response, result);
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        OrderItemRequest request = new OrderItemRequest();
        OrderItem orderItem = new OrderItem();
        OrderItem updatedOrderItem = new OrderItem();
        OrderItemResponse response = new OrderItemResponse();

        when(orderItemMapper.toEntity(id, request)).thenReturn(orderItem);
        when(orderItemRepository.save(orderItem)).thenReturn(updatedOrderItem);
        when(orderItemMapper.toResponse(updatedOrderItem)).thenReturn(response);

        OrderItemResponse result = orderItemService.update(id, request);

        assertEquals(response, result);
    }

    @Test
    void testSelectById() {
        Long id = 1L;
        OrderItem orderItem = new OrderItem();
        OrderItemResponse response = new OrderItemResponse();

        when(orderItemRepository.findById(id)).thenReturn(Optional.of(orderItem));
        when(orderItemMapper.toResponse(orderItem)).thenReturn(response);

        OrderItemResponse result = orderItemService.selectById(id);

        assertEquals(response, result);
    }

    @Test
    void testSelectAll() {
        PageFilter filter = new PageFilter();
        Pageable pageable = Pageable.unpaged();
        Page<OrderItem> orderItems = new PageImpl<>(Arrays.asList(new OrderItem()));
        PageResponse<OrderItemResponse> response = new PageResponse<>();

        when(createPageable.execute(filter)).thenReturn(pageable);
        when(orderItemRepository.findAll(pageable)).thenReturn(orderItems);
        when(orderItemMapper.toResponsePage(orderItems)).thenReturn(response);

        PageResponse<OrderItemResponse> result = orderItemService.selectAll(filter);

        assertEquals(response, result);
    }

    @Test
    void testSelectAllByOrderId() {
        Long orderId = 1L;
        PageFilter filter = new PageFilter();
        Pageable pageable = Pageable.unpaged();
        Orders order = new Orders();
        List<OrderItem> ordersAll = Arrays.asList(new OrderItem());
        Page<OrderItem> orderItems = new PageImpl<>(ordersAll);
        OrderItemPageResponse response = new OrderItemPageResponse();

        when(orderItemMapper.toOrderEntity(orderId)).thenReturn(order);
        when(createPageable.execute(filter)).thenReturn(pageable);
        when(orderItemRepository.findByOrder(order)).thenReturn(ordersAll);
        when(orderItemRepository.findByOrder(order, pageable)).thenReturn(orderItems);
        when(orderItemMapper.toResponsePage(ordersAll, orderItems)).thenReturn(response);

        OrderItemPageResponse result = orderItemService.selectAllByOrderId(orderId, filter);

        assertEquals(response, result);
    }
}
