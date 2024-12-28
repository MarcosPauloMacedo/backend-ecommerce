package com.backend_ecommerce.backend_ecommerce.interfaces.product;

import com.backend_ecommerce.backend_ecommerce.interfaces.shared.DataService;
import com.backend_ecommerce.backend_ecommerce.models.request.ProductRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.ProductResponse;

public interface DataServiceProduct extends DataService <ProductResponse,ProductRequest> {}
