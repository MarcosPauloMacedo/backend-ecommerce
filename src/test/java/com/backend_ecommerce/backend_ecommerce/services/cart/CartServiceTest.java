package com.backend_ecommerce.backend_ecommerce.services.cart;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.backend_ecommerce.backend_ecommerce.models.entity.Cart;
import com.backend_ecommerce.backend_ecommerce.models.entity.User;
import com.backend_ecommerce.backend_ecommerce.models.mapper.CartMapper;
import com.backend_ecommerce.backend_ecommerce.models.repository.CartRepository;
import com.backend_ecommerce.backend_ecommerce.models.request.CartRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.CartResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;
import com.backend_ecommerce.backend_ecommerce.services.shared.CreatePageable;

import java.util.Optional;
import java.util.Collections;
import java.util.List;

public class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartMapper cartMapper;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CreatePageable createPageable;

    @Mock
    private CalculateTotalPrice calculateTotalPrice;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        CartRequest request = new CartRequest();
        Cart cart = new Cart();
        Cart savedCart = new Cart();
        CartResponse response = new CartResponse();

        when(cartMapper.toEntity(request)).thenReturn(cart);
        when(cartRepository.save(cart)).thenReturn(savedCart);
        when(cartMapper.toResponse(savedCart)).thenReturn(response);

        CartResponse result = cartService.save(request);

        assertEquals(response, result);
        verify(cartMapper).toEntity(request);
        verify(cartRepository).save(cart);
        verify(cartMapper).toResponse(savedCart);
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        CartRequest request = new CartRequest();
        Cart cart = new Cart();
        Cart updatedCart = new Cart();
        CartResponse response = new CartResponse();

        when(cartMapper.toEntity(id, request)).thenReturn(cart);
        when(cartRepository.save(cart)).thenReturn(updatedCart);
        when(cartMapper.toResponse(updatedCart)).thenReturn(response);

        CartResponse result = cartService.update(id, request);

        assertEquals(response, result);
        verify(cartMapper).toEntity(id, request);
        verify(cartRepository).save(cart);
        verify(cartMapper).toResponse(updatedCart);
    }

    @Test
    public void testSelectById() {
        Long id = 1L;
        Cart cart = new Cart();
        CartResponse response = new CartResponse();

        when(cartRepository.findById(id)).thenReturn(Optional.of(cart));
        when(cartMapper.toResponse(cart)).thenReturn(response);

        CartResponse result = cartService.selectById(id);

        assertEquals(response, result);
        verify(cartRepository).findById(id);
        verify(cartMapper).toResponse(cart);
    }

    @Test
    public void testSelectAll() {
        PageFilter pageFilter = new PageFilter();
        Pageable pageable = mock(Pageable.class);
        Page<Cart> carts = new PageImpl<>(Collections.emptyList());
        PageResponse<CartResponse> response = new PageResponse<>();

        when(createPageable.execute(pageFilter)).thenReturn(pageable);
        when(cartRepository.findAll(pageable)).thenReturn(carts);
        when(cartMapper.toResponsePage(carts)).thenReturn(response);

        PageResponse<CartResponse> result = cartService.selectAll(pageFilter);

        assertEquals(response, result);
        verify(createPageable).execute(pageFilter);
        verify(cartRepository).findAll(pageable);
        verify(cartMapper).toResponsePage(carts);
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        Cart cart = new Cart();

        when(cartRepository.findById(id)).thenReturn(Optional.of(cart));

        cartService.delete(id);

        verify(cartRepository).findById(id);
        verify(cartRepository).delete(cart);
    }

    @Test
    public void testCalculateTotalPriceCartByUser() {
        Long userId = 1L;
        User user = new User();
        List<Cart> carts = Collections.emptyList();
        String totalPrice = "100.00";

        when(cartMapper.toUserEntity(userId)).thenReturn(user);
        when(cartRepository.findByUser(user)).thenReturn(carts);
        when(calculateTotalPrice.execute(carts)).thenReturn(totalPrice);

        String result = cartService.calculateTotalPriceCartByUser(userId);

        assertEquals(totalPrice, result);
        verify(cartMapper).toUserEntity(userId);
        verify(cartRepository).findByUser(user);
        verify(calculateTotalPrice).execute(carts);
    }
}
