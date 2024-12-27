package com.backend_ecommerce.backend_ecommerce.models.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.backend_ecommerce.backend_ecommerce.interfaces.product.MapperProduct;
import com.backend_ecommerce.backend_ecommerce.models.entity.Product;
import com.backend_ecommerce.backend_ecommerce.models.request.ProductRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
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
    public Product toEntity(Long id, ProductRequest request) {
        Product product = this.toEntity(request);
        product.setId(id);

        return product;
    }

    @Override
    public ProductResponse toResponse(Product entity) {
        return modelMapper.map(entity, ProductResponse.class);
    }

    @Override
    public List<ProductResponse> toResponseList(List<Product> entities) {
        return entities.stream().map(entity -> this.toResponse(entity)).toList();
    }

    @Override
    public PageResponse<ProductResponse> toResponsePage(Page<Product> entities) {
        PageResponse<ProductResponse> pageResponse = new PageResponse<>();
        pageResponse.setContent(this.toResponseList(entities.getContent()));
        pageResponse.setTotalPages(entities.getTotalPages());

        return pageResponse;
    }
}
