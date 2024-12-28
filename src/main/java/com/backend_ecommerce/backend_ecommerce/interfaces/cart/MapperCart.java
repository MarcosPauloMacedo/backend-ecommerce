package com.backend_ecommerce.backend_ecommerce.interfaces.cart;

import com.backend_ecommerce.backend_ecommerce.interfaces.shared.Mapper;
import com.backend_ecommerce.backend_ecommerce.models.entity.Cart;
import com.backend_ecommerce.backend_ecommerce.models.request.CartRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.CartResponse;

public interface MapperCart extends Mapper<CartRequest,CartResponse,Cart> {}
