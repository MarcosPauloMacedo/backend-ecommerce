package com.backend_ecommerce.backend_ecommerce.models.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.backend_ecommerce.backend_ecommerce.interfaces.cart.MapperCart;
import com.backend_ecommerce.backend_ecommerce.models.entity.Cart;
import com.backend_ecommerce.backend_ecommerce.models.entity.User;
import com.backend_ecommerce.backend_ecommerce.models.request.CartRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.CartResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;

@Component
public class CartMapper implements MapperCart {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Cart toEntity(CartRequest request) {
        return modelMapper.map(request, Cart.class);
    }
    
    @Override
    public Cart toEntity(Long id, CartRequest request) {
        Cart cart = this.toEntity(request);
        cart.setId(id);

        return cart;
    }

    @Override
    public User toUserEntity(Long userId) {
        User user = new User();
        user.setId(userId);

        return user;
    }

    @Override
    public CartResponse toResponse(Cart entity) {
        CartResponse cartResponse = modelMapper.map(entity, CartResponse.class);
        cartResponse.setProduct(productMapper.toResponse(entity.getProduct()));
        cartResponse.setUser(userMapper.toResponse(entity.getUser()));

        return cartResponse;
    }

    @Override
    public List<CartResponse> toResponseList(List<Cart> entities) {
        return entities.stream().map(entity -> this.toResponse(entity)).toList();
    }

    @Override
    public PageResponse<CartResponse> toResponsePage(Page<Cart> entities) {
        PageResponse<CartResponse> pageResponse = new PageResponse<>();
        pageResponse.setContent(this.toResponseList(entities.getContent()));
        pageResponse.setTotalPages(entities.getTotalPages());

        return pageResponse;
    }
}
