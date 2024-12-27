package com.backend_ecommerce.backend_ecommerce.services.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.interfaces.product.DataServiceProduct;
import com.backend_ecommerce.backend_ecommerce.models.entity.Product;
import com.backend_ecommerce.backend_ecommerce.models.mapper.ProductMapper;
import com.backend_ecommerce.backend_ecommerce.models.repository.ProductRepository;
import com.backend_ecommerce.backend_ecommerce.models.request.ProductRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.ProductResponse;

@Service
public class ProductService implements DataServiceProduct {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public ProductResponse save(ProductRequest request) {
        Product product = productMapper.toEntity(request);
        Product productSave = productRepository.save(product);

        return productMapper.toResponse(productSave);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productMapper.toEntity(id,request);
        Product productUpdate = productRepository.save(product);

        return productMapper.toResponse(productUpdate);
    }

    @Override
    public ProductResponse selectById(Long id) {
        Product product = productRepository.findById(id).get();
        return productMapper.toResponse(product);
    }

    @Override
    public PageResponse<ProductResponse> selectAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Product> products = productRepository.findAll(pageable);

        return productMapper.toResponsePage(products);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }
}
