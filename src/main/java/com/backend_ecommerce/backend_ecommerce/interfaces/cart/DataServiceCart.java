package com.backend_ecommerce.backend_ecommerce.interfaces.cart;

import com.backend_ecommerce.backend_ecommerce.interfaces.shared.DataService;
import com.backend_ecommerce.backend_ecommerce.interfaces.shared.SelectAll;
import com.backend_ecommerce.backend_ecommerce.models.request.CartRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.CartResponse;

public interface DataServiceCart extends DataService<CartResponse, CartRequest>,
SelectAll<CartResponse> {
    String calculateTotalPriceCartByUser(Long userId);
    String calculatePricePerItemOfCart(Long cartId);
}
