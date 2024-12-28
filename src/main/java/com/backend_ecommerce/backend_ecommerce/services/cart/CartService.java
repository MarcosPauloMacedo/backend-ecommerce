package com.backend_ecommerce.backend_ecommerce.services.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.interfaces.cart.DataServiceCart;
import com.backend_ecommerce.backend_ecommerce.models.entity.Cart;
import com.backend_ecommerce.backend_ecommerce.models.mapper.CartMapper;
import com.backend_ecommerce.backend_ecommerce.models.repository.CartRepository;
import com.backend_ecommerce.backend_ecommerce.models.request.CartRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.CartResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;

@Service
public class CartService implements DataServiceCart {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartResponse save(CartRequest request) {
        Cart cart = cartMapper.toEntity(request);
        Cart cartSave = cartRepository.save(cart);

        return cartMapper.toResponse(cartSave);
    }

    @Override
    public CartResponse update(Long id, CartRequest request) {
        Cart cart = cartMapper.toEntity(id,request);
        Cart cartUpdate = cartRepository.save(cart);

        return cartMapper.toResponse(cartUpdate);
    }

    @Override
    public CartResponse selectById(Long id) {
        Cart cart = cartRepository.findById(id).get();
        return cartMapper.toResponse(cart);
    }

    @Override
    public PageResponse<CartResponse> selectAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Cart> carts = cartRepository.findAll(pageable);

        return cartMapper.toResponsePage(carts);
    }

    @Override
    public void delete(Long id) {
        Cart cart = cartRepository.findById(id).get();
        cartRepository.delete(cart);
    }
    
}
