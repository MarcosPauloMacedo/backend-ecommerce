package com.backend_ecommerce.backend_ecommerce.controllers;

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

import com.backend_ecommerce.backend_ecommerce.models.request.CartRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.CartResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;
import com.backend_ecommerce.backend_ecommerce.services.cart.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cart")
@Tag(name = "Carrinho de compras", description = "Gerenciamento de carrinhos de compras")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping()
    @Operation(summary = "Salvar um carrinho de compras")
    public CartResponse save(@RequestBody CartRequest request) {
        return cartService.save(request);
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Listar todos os carrinhos de compras")
    public PageResponse<CartResponse> selectAll(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "ASC") String sortOrder,
    @RequestParam(defaultValue = "id") String sortField

    ) {
        var pageFilter = new PageFilter(page, size, sortOrder, sortField);
        return cartService.selectAll(pageFilter);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um carrinho de compras pelo ID")
    public CartResponse selectById(@PathVariable Long id) {
        return cartService.selectById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um carrinho de compras pelo ID")
    public CartResponse update(@PathVariable Long id, @RequestBody CartRequest request) {
        return cartService.update(id, request);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um carrinho de compras pelo ID")
    public void delete(@PathVariable Long id) {
        cartService.delete(id);
    }

    @GetMapping("/totalPrice/{userId}")
    @Operation(summary = "Calcular o preço total do carrinho de compras de um usuário")
    public String calculateTotalPriceCartByUser(@PathVariable Long userId) {
        return cartService.calculateTotalPriceCartByUser(userId);
    }

    @GetMapping("/amountToPayPerItem/{id}")
    @Operation(summary = "Calcular o valor a ser pago por item")
    public String calculatePricePerItemOfCart(@PathVariable Long id) {
        return cartService.calculatePricePerItemOfCart(id);
    }
}
