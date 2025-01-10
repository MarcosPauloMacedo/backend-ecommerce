package com.backend_ecommerce.backend_ecommerce.interfaces.shared;

import java.util.List;

import com.backend_ecommerce.backend_ecommerce.models.request.StockRequest;
import com.backend_ecommerce.backend_ecommerce.models.request.ProductAndQuantityRequest;

public interface StockDataService {
    public void validateIsExitsQuantityInStock(Long id, Integer quantity);
    public void validateIsExitsQuantityInStock(List<ProductAndQuantityRequest> request);
    public void removeStock(Long id, Integer quantity);
    public void removeStock(StockRequest request);
    public void addStock(StockRequest request);
}
