package com.backend_ecommerce.backend_ecommerce.interfaces.product;

import com.backend_ecommerce.backend_ecommerce.interfaces.shared.Mapper;
import com.backend_ecommerce.backend_ecommerce.models.entity.Product;
import com.backend_ecommerce.backend_ecommerce.models.request.ProductRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.ProductResponse;

public interface MapperProduct extends Mapper<
ProductRequest,ProductResponse, Product> {}
