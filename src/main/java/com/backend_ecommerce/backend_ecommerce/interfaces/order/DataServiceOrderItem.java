package com.backend_ecommerce.backend_ecommerce.interfaces.order;

import com.backend_ecommerce.backend_ecommerce.interfaces.shared.DataService;
import com.backend_ecommerce.backend_ecommerce.interfaces.shared.SelectAll;
import com.backend_ecommerce.backend_ecommerce.models.request.OrderItemRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderItemPageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderItemResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;

public interface DataServiceOrderItem extends DataService<OrderItemResponse, 
OrderItemRequest>, SelectAll<OrderItemResponse> {
    OrderItemPageResponse selectAllByOrderId(Long orderId, PageFilter filter);
} 
