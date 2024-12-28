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

import com.backend_ecommerce.backend_ecommerce.models.request.CartRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.CartResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.services.cart.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping()
    public CartResponse save(@RequestBody CartRequest request) {
        return cartService.save(request);
    }

    @GetMapping()
    public PageResponse<CartResponse> selectAll(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size
    ) {
        return cartService.selectAll(page, size);
    }

    @GetMapping("/{id}")
    public CartResponse selectById(@PathVariable Long id) {
        return cartService.selectById(id);
    }

    @PutMapping("/{id}")
    public CartResponse update(@PathVariable Long id, @RequestBody CartRequest request) {
        return cartService.update(id, request);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cartService.delete(id);
    }
}
