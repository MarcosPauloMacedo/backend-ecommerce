package com.backend_ecommerce.backend_ecommerce.services.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.interfaces.cart.DataServiceCart;
import com.backend_ecommerce.backend_ecommerce.models.entity.Cart;
import com.backend_ecommerce.backend_ecommerce.models.entity.User;
import com.backend_ecommerce.backend_ecommerce.models.mapper.CartMapper;
import com.backend_ecommerce.backend_ecommerce.models.repository.CartRepository;
import com.backend_ecommerce.backend_ecommerce.models.request.CartRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.CartResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;
import com.backend_ecommerce.backend_ecommerce.services.shared.CalculateAmountToPayPerItem;
import com.backend_ecommerce.backend_ecommerce.services.shared.CreatePageable;
import com.backend_ecommerce.backend_ecommerce.services.shared.StockService;
import com.backend_ecommerce.backend_ecommerce.services.shared.ValidateIfExistsById;

@Service
public class CartService implements DataServiceCart {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CreatePageable createPageable;

    @Autowired
    private ValidateIfExistsById validateIfExistsById;

    @Autowired
    private CalculateTotalPriceCart calculateTotalPriceCart;

    @Autowired
    private CalculateAmountToPayPerItem calculateAmountToPayPerItem;

    @Autowired
    private StockService stockService;

    @Override
    public CartResponse save(CartRequest request) {
        stockService.validateIsExitsQuantityInStock(
            request.getProduct().getId(), 
            request.getQuantity()
        );
        
        Cart cart = cartMapper.toEntity(request);
        Cart cartSave = cartRepository.save(cart);

        return cartMapper.toResponse(cartSave);
    }

    @Override
    public CartResponse update(Long id, CartRequest request) {
        validateIfExistsById.inCart(id);

        Cart cart = cartMapper.toEntity(id,request);
        Cart cartUpdate = cartRepository.save(cart);

        return cartMapper.toResponse(cartUpdate);
    }

    @Override
    public CartResponse selectById(Long id) {
        validateIfExistsById.inCart(id);

        Cart cart = cartRepository.findById(id).get();

        return cartMapper.toResponse(cart);
    }

    @Override
    public PageResponse<CartResponse> selectAll(PageFilter pageFilter) {
        Pageable pageable = createPageable.execute(pageFilter);

        Page<Cart> carts = cartRepository.findAll(pageable);

        return cartMapper.toResponsePage(carts);
    }

    @Override
    public void delete(Long id) {
        validateIfExistsById.inCart(id);
        cartRepository.deleteById(id);
    }

    @Override
    public String calculateTotalPriceCartByUser(Long userId) {
        validateIfExistsById.inUser(userId);

        User user = cartMapper.toUserEntity(userId);
        List<Cart> carts = cartRepository.findByUser(user);
        
        return calculateTotalPriceCart.execute(carts);
    }

    @Override
    public String calculatePricePerItemOfCart(Long id) {
        validateIfExistsById.inCart(id);
        
        Cart cart = cartRepository.findById(id).get();

        return calculateAmountToPayPerItem.execute(
            cart.getQuantity(),
            cart.getProduct().getPrice()
        );
    }
}
