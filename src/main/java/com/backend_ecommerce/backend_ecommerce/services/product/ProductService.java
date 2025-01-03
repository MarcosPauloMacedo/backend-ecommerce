package com.backend_ecommerce.backend_ecommerce.services.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.interfaces.product.DataServiceProduct;
import com.backend_ecommerce.backend_ecommerce.models.entity.Product;
import com.backend_ecommerce.backend_ecommerce.models.mapper.ProductMapper;
import com.backend_ecommerce.backend_ecommerce.models.repository.ProductRepository;
import com.backend_ecommerce.backend_ecommerce.models.request.ProductRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.ProductResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageProductFilter;
import com.backend_ecommerce.backend_ecommerce.services.shared.CreatePageable;
import com.backend_ecommerce.backend_ecommerce.services.shared.ValidateIfExistsById;

@Service
public class ProductService implements DataServiceProduct {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CreatePageable createPageable;

    @Autowired
    private ProductSearch productSearch;

    @Autowired
    private ValidateIfExistsById validateIfExistsById;
    
    @Override
    public ProductResponse save(ProductRequest request) {
        Product product = productMapper.toEntity(request);
        Product productSave = productRepository.save(product);

        return productMapper.toResponse(productSave);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        validateIfExistsById.inProduct(id);

        Product product = productMapper.toEntity(id,request);
        Product productUpdate = productRepository.save(product);

        return productMapper.toResponse(productUpdate);
    }

    @Override
    public ProductResponse selectById(Long id) {
        validateIfExistsById.inProduct(id);

        Product product = productRepository.findById(id).get();

        return productMapper.toResponse(product);
    }

    @Override
    public PageResponse<ProductResponse> selectAll(PageFilter pageFilter,
    PageProductFilter pageProductFilter) {
        
        Pageable pageable = createPageable.execute(pageFilter);
        Specification<Product> specification = productSearch.execute(pageProductFilter);

        Page<Product> products = productRepository.findAll(specification,pageable);

        return productMapper.toResponsePage(products);
    }

    @Override
    public void delete(Long id) {
        validateIfExistsById.inProduct(id);
        productRepository.deleteById(id);
    }
}
