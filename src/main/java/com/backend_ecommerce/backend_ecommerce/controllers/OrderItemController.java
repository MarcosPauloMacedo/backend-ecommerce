package com.backend_ecommerce.backend_ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend_ecommerce.backend_ecommerce.models.request.OrderItemRequest;
import com.backend_ecommerce.backend_ecommerce.models.request.ProductAndQuantityRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderItemPageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.OrderItemResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;
import com.backend_ecommerce.backend_ecommerce.services.order.OrderItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/order-item")
@Tag(name = "Itens do Pedido", description = "Gerenciamento de itens do pedido")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping()
    @Operation(summary = "Salvar um item do pedido")
    public OrderItemResponse save(@RequestBody OrderItemRequest request) {
        return orderItemService.save(request);
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Listar todos os itens do pedido")
    public PageResponse<OrderItemResponse> selectAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "ASC") String sortOrder,
        @RequestParam(defaultValue = "id") String sortField
    ) {
        var pageFilter = new PageFilter(page, size, sortOrder, sortField);
        return orderItemService.selectAll(pageFilter);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Buscar um item do pedido pelo ID")
    public OrderItemResponse selectById(@PathVariable Long id) {
        return orderItemService.selectById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Atualizar um item do pedido pelo ID")
    public OrderItemResponse update(@PathVariable Long id, @RequestBody OrderItemRequest request) {
        return orderItemService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Deletar um item do pedido pelo ID")
    public void delete(@PathVariable Long id) {
        orderItemService.delete(id);
    }    

    @GetMapping("/order/{orderId}")
    @Operation(summary = "Listar todos os itens do pedido pelo ID do pedido")
    public OrderItemPageResponse selectByOrderId(
        @PathVariable Long orderId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "ASC") String sortOrder,
        @RequestParam(defaultValue = "id") String sortField
    ) {
        var pageFilter = new PageFilter(page, size, sortOrder, sortField);
        return orderItemService.selectAllByOrderId(orderId, pageFilter);
    }

    @PostMapping("/saveAll/{userId}")
    @Operation(summary = "Salvar todos os itens do pedido baseado no ID do usuário")
    public void saveAll(
        @PathVariable Long userId,
        @RequestBody List<ProductAndQuantityRequest> request
    ) {
        orderItemService.saveAll(userId,request);
    }
}
