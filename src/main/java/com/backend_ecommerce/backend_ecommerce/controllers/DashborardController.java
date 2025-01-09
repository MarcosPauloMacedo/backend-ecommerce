package com.backend_ecommerce.backend_ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend_ecommerce.backend_ecommerce.models.response.OrderItemPageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.ProductResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageOrderItemsFilter;
import com.backend_ecommerce.backend_ecommerce.services.order.OrderItemService;
import com.backend_ecommerce.backend_ecommerce.services.product.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/dashboard")
@Tag(name = "Dashboard", description = "Gerenciamento do dashboard")
public class DashborardController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/low-stock")
    @Operation(summary = "Listar produtos com estoque baixo")
    public PageResponse<ProductResponse> selectByLowStock(
        @RequestParam(defaultValue = "5") int quantity,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "ASC") String sortOrder,
        @RequestParam(defaultValue = "id") String sortField
    ) { 
        var pageFilter = new PageFilter(page, size, sortOrder, sortField);
        return productService.selectByLowStock(quantity,pageFilter);
    }

    @GetMapping("/total-sales")
    @Operation(summary = "Listar total de vendas")
    public OrderItemPageResponse selectAllAndGetTotalSales(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "ASC") String sortOrder,
        @RequestParam(defaultValue = "id") String sortField,
        @RequestParam(required = false) String category
    ) { 
        var pageFilter = new PageFilter(page, size, sortOrder, sortField);
        var pageOrderItemsFilter = new PageOrderItemsFilter(category);

        return orderItemService.selectAllAndGetTotalSales(pageFilter, pageOrderItemsFilter);
    }
}
