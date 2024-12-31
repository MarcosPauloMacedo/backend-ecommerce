package com.backend_ecommerce.backend_ecommerce.interfaces.cart;

import com.backend_ecommerce.backend_ecommerce.interfaces.shared.DataService;
import com.backend_ecommerce.backend_ecommerce.models.request.CartRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.CartResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;

public interface DataServiceCart extends DataService<CartResponse, CartRequest> {
    PageResponse<CartResponse> selectAll(PageFilter pageFilter);
    String calculateTotalPriceCartByUser(Long userId);
}
