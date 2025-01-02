package com.backend_ecommerce.backend_ecommerce.interfaces.order;

import com.backend_ecommerce.backend_ecommerce.interfaces.shared.Mapper;
import com.backend_ecommerce.backend_ecommerce.models.entity.Orders;
import com.backend_ecommerce.backend_ecommerce.models.request.OrderRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderResponse;

public interface MapperOrder extends Mapper<OrderRequest, OrderResponse, Orders> {}
