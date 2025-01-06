package com.backend_ecommerce.backend_ecommerce.interfaces.order;

import com.backend_ecommerce.backend_ecommerce.interfaces.shared.DataService;
import com.backend_ecommerce.backend_ecommerce.interfaces.shared.SelectAll;
import com.backend_ecommerce.backend_ecommerce.models.request.OrderRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderResponse;

public interface DataServiceOrder extends DataService<OrderResponse, OrderRequest>, 
SelectAll<OrderResponse> {
    OrderResponse saveByUser(Long userId);
    String findEmailByOrderId(Long orderId);
}
