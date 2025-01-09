package com.backend_ecommerce.backend_ecommerce.interfaces.order;

import java.util.List;

import com.backend_ecommerce.backend_ecommerce.interfaces.shared.DataService;
import com.backend_ecommerce.backend_ecommerce.interfaces.shared.SelectAll;
import com.backend_ecommerce.backend_ecommerce.models.request.OrderItemRequest;
import com.backend_ecommerce.backend_ecommerce.models.request.ProductAndQuantityRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderItemPageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderItemResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageOrderItemsFilter;

public interface DataServiceOrderItem extends DataService<OrderItemResponse, 
OrderItemRequest>, SelectAll<OrderItemResponse> {
    OrderItemPageResponse selectAllByOrderId(Long orderId, PageFilter filter);

    void saveAll(Long userId, List<ProductAndQuantityRequest> request);
    
    OrderItemPageResponse selectAllAndGetTotalSales(PageFilter filter,
    PageOrderItemsFilter pageOrderItemsFilter);
} 
