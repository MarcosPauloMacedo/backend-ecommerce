package com.backend_ecommerce.backend_ecommerce.services.shared;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.interfaces.shared.StockDataService;
import com.backend_ecommerce.backend_ecommerce.models.entity.Product;
import com.backend_ecommerce.backend_ecommerce.models.repository.ProductRepository;
import com.backend_ecommerce.backend_ecommerce.models.request.StockRequest;
import com.backend_ecommerce.backend_ecommerce.models.request.ProductAndQuantityRequest;

@Service
public class StockService implements StockDataService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ValidateIfExistsById validateIfExistsById;

    @Override
    public void validateIsExitsQuantityInStock(Long id, Integer quantity) {
        validateIfExistsById.inProduct(id);

        var product = productRepository.findById(id).get();

        validateIfItIsInStock(product, quantity);
    }

    @Override
    public void validateIsExitsQuantityInStock(List<ProductAndQuantityRequest> request) {
        request.forEach(req -> {
            validateIsExitsQuantityInStock(req.getProduct().getId(), req.getQuantity());
        });
    }

    @Override
    public void removeStock(Long id, Integer quantity) {
        validateIfExistsById.inProduct(id);

        var product = productRepository.findById(id).get();

        validateIfItIsInStock(product, quantity);

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

    @Override
    public void removeStock(StockRequest request) {
        removeStock(request.getProductId(), request.getQuantity());
    }

    @Override
    public void addStock(StockRequest request) {
        Long id = request.getProductId();

        validateIfExistsById.inProduct(id);

        var product = productRepository.findById(id).get();

        product.setStock(product.getStock() + request.getQuantity());
        productRepository.save(product);
    }

    private void validateIfItIsInStock(Product product, int quantity) {
        if(product.getStock() < quantity) {
            throw new IllegalArgumentException("Quantidade insuficiente no estoque do produto: "
            + product.getName());
        }
    }
}
