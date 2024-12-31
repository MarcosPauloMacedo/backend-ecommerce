package com.backend_ecommerce.backend_ecommerce.services.cart;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backend_ecommerce.backend_ecommerce.models.entity.Cart;
import com.backend_ecommerce.backend_ecommerce.models.entity.Product;

public class CalculateTotalPriceTest {

    private CalculateTotalPrice calculateTotalPrice;

    @BeforeEach
    public void setUp() {
        calculateTotalPrice = new CalculateTotalPrice();
    }

    @Test
    public void testExecute() {
        Product product1 = new Product();
        product1.setPrice(100.0);

        Product product2 = new Product();
        product2.setPrice(200.0);

        Cart cart1 = new Cart();
        cart1.setProduct(product1);
        cart1.setQuantity(2);

        Cart cart2 = new Cart();
        cart2.setProduct(product2);
        cart2.setQuantity(1);

        List<Cart> cartList = Arrays.asList(cart1, cart2);

        String result = calculateTotalPrice.execute(cartList);

        assertEquals("R$ 400,00", result);
    }

    @Test
    public void testExecute_EmptyCart() {
        List<Cart> cartList = Arrays.asList();

        String result = calculateTotalPrice.execute(cartList);

        assertEquals("R$ 0,00", result);
    }
}
