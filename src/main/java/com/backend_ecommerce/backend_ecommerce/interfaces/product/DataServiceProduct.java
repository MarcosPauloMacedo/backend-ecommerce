package com.backend_ecommerce.backend_ecommerce.interfaces.product;

import com.backend_ecommerce.backend_ecommerce.interfaces.shared.DataService;
import com.backend_ecommerce.backend_ecommerce.models.request.ProductRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.ProductResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageProductFilter;

public interface DataServiceProduct extends DataService <ProductResponse,ProductRequest> {
    PageResponse<ProductResponse> selectAll(PageFilter pageFilter,
    PageProductFilter pageProductFilter);

    PageResponse<ProductResponse> selectByLowStock(int quantity, 
    PageFilter pageFilter);
}
