package com.backend_ecommerce.backend_ecommerce.services.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.interfaces.product.DataServiceProduct;
import com.backend_ecommerce.backend_ecommerce.interfaces.shared.Page;
import com.backend_ecommerce.backend_ecommerce.models.entity.Product;
import com.backend_ecommerce.backend_ecommerce.models.mapper.ProductMapper;
import com.backend_ecommerce.backend_ecommerce.models.repository.ProductRepository;
import com.backend_ecommerce.backend_ecommerce.models.request.ProductRequest;
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
    public ProductResponse update(ProductRequest request) {
        return null;
    }

    @Override
    public ProductResponse selectById(Long id) {
        Product product = productRepository.findById(id).get();
        return productMapper.toResponse(product);
    }

    @Override
    public Page<ProductResponse> selectAll(int page, int size) {
        return null;
    }

    @Override
    public void delete(ProductRequest request) {
        
    }
    
}
