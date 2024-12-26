package com.backend_ecommerce.backend_ecommerce.models.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend_ecommerce.backend_ecommerce.interfaces.product.MapperProduct;
import com.backend_ecommerce.backend_ecommerce.models.entity.Product;
import com.backend_ecommerce.backend_ecommerce.models.request.ProductRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.ProductResponse;

@Component
public class ProductMapper implements MapperProduct {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Product toEntity(ProductRequest request) {
        return modelMapper.map(request, Product.class);
    }

    @Override
    public ProductResponse toResponse(Product entity) {
        return modelMapper.map(entity, ProductResponse.class);
    }
}
