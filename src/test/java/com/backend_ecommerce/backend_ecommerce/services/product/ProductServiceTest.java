package com.backend_ecommerce.backend_ecommerce.services.product;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.backend_ecommerce.backend_ecommerce.models.entity.Product;
import com.backend_ecommerce.backend_ecommerce.models.mapper.ProductMapper;
import com.backend_ecommerce.backend_ecommerce.models.repository.ProductRepository;
import com.backend_ecommerce.backend_ecommerce.models.request.ProductRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.ProductResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageProductFilter;
import com.backend_ecommerce.backend_ecommerce.services.shared.CreatePageable;

import java.util.Optional;
import java.util.Collections;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CreatePageable createPageable;

    @Mock
    private ProductSearch productSearch;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        ProductRequest request = new ProductRequest();
        Product product = new Product();
        Product savedProduct = new Product();
        ProductResponse response = new ProductResponse();

        when(productMapper.toEntity(request)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(savedProduct);
        when(productMapper.toResponse(savedProduct)).thenReturn(response);

        ProductResponse result = productService.save(request);

        assertEquals(response, result);
        verify(productMapper).toEntity(request);
        verify(productRepository).save(product);
        verify(productMapper).toResponse(savedProduct);
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        ProductRequest request = new ProductRequest();
        Product product = new Product();
        Product updatedProduct = new Product();
        ProductResponse response = new ProductResponse();

        when(productMapper.toEntity(id, request)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(updatedProduct);
        when(productMapper.toResponse(updatedProduct)).thenReturn(response);

        ProductResponse result = productService.update(id, request);

        assertEquals(response, result);
        verify(productMapper).toEntity(id, request);
        verify(productRepository).save(product);
        verify(productMapper).toResponse(updatedProduct);
    }

    @Test
    public void testSelectById() {
        Long id = 1L;
        Product product = new Product();
        ProductResponse response = new ProductResponse();

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productMapper.toResponse(product)).thenReturn(response);

        ProductResponse result = productService.selectById(id);

        assertEquals(response, result);
        verify(productRepository).findById(id);
        verify(productMapper).toResponse(product);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSelectAll() {
        PageFilter pageFilter = new PageFilter();
        PageProductFilter pageProductFilter = new PageProductFilter();
        Pageable pageable = mock(Pageable.class);
        Specification<Product> specification = mock(Specification.class);
        Page<Product> products = new PageImpl<>(Collections.emptyList());
        PageResponse<ProductResponse> response = new PageResponse<>();

        when(createPageable.execute(pageFilter)).thenReturn(pageable);
        when(productSearch.execute(pageProductFilter)).thenReturn(specification);
        when(productRepository.findAll(specification, pageable)).thenReturn(products);
        when(productMapper.toResponsePage(products)).thenReturn(response);

        PageResponse<ProductResponse> result = productService.selectAll(pageFilter, pageProductFilter);

        assertEquals(response, result);
        verify(createPageable).execute(pageFilter);
        verify(productSearch).execute(pageProductFilter);
        verify(productRepository).findAll(specification, pageable);
        verify(productMapper).toResponsePage(products);
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        Product product = new Product();

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        productService.delete(id);

        verify(productRepository).findById(id);
        verify(productRepository).delete(product);
    }
}
