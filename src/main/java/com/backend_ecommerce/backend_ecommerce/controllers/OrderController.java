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

import com.backend_ecommerce.backend_ecommerce.models.request.OrderRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;
import com.backend_ecommerce.backend_ecommerce.services.order.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/order")
@Tag(name = "Pedidos", description = "Gerenciamento de pedidos")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping()
    @Operation(summary = "Salvar um pedido")
    public OrderResponse save(@RequestBody OrderRequest request) {
        return orderService.save(request);
    }

    @GetMapping()
    @Operation(summary = "Listar todos os pedidos")
    public PageResponse<OrderResponse> selectAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "ASC") String sortOrder,
        @RequestParam(defaultValue = "id") String sortField
    ) {
        var pageFilter = new PageFilter(page, size, sortOrder, sortField);
        return orderService.selectAll(pageFilter);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um pedido pelo ID")
    public OrderResponse selectById(@PathVariable Long id) {
        return orderService.selectById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um pedido pelo ID")
    public OrderResponse update(@PathVariable Long id, @RequestBody OrderRequest request) {
        return orderService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um pedido pelo ID")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}
