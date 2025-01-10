package com.backend_ecommerce.backend_ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend_ecommerce.backend_ecommerce.models.request.StockRequest;
import com.backend_ecommerce.backend_ecommerce.services.shared.StockService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/stock")
@Tag(name = "Estoque", description = "Gerenciamento de estoque")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/add")
    @Operation(summary = "Aumentar a quantidade de estoque de um produto")
    public void addStock(@RequestBody StockRequest request) {
        stockService.addStock(request);
    }

    @PostMapping("/remove")
    @Operation(summary = "Remover a quantidade de estoque de um produto")
    public void removeStock(@RequestBody StockRequest request) {
        stockService.removeStock(request);
    }
}
