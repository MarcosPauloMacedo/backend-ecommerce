package com.backend_ecommerce.backend_ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend_ecommerce.backend_ecommerce.models.request.ProductRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.ProductResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageProductFilter;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;
import com.backend_ecommerce.backend_ecommerce.services.product.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping()
    public ProductResponse save(@RequestBody ProductRequest request) {
        return productService.save(request);
    }

    @GetMapping()
    public PageResponse<ProductResponse> selectAll(
    @RequestParam(defaultValue = "0") int page, 
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "ASC") String sortOrder,
    @RequestParam(defaultValue = "id") String sortField,
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String category,
    @RequestParam(required = false) Double minPrice,
    @RequestParam(required = false) Double maxPrice) {

        var pageFilter = new PageFilter(page, size, sortOrder, sortField);
        var pageProductFilter = new PageProductFilter(name, category, minPrice, maxPrice);

        return productService.selectAll(pageFilter, pageProductFilter);
    }

    @GetMapping("/{id}")
    public ProductResponse selectById(@PathVariable Long id) {
        return productService.selectById(id);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id, 
    @RequestBody ProductRequest request) {
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
