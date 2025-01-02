package com.backend_ecommerce.backend_ecommerce.services.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.backend_ecommerce.backend_ecommerce.models.entity.Cart;
import com.backend_ecommerce.backend_ecommerce.models.entity.Product;
import com.backend_ecommerce.backend_ecommerce.services.shared.CalculatePricePerQuantity;
import com.backend_ecommerce.backend_ecommerce.services.shared.FormatToBrazilianCurrency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CalculateTotalPriceCartTest {

    @InjectMocks
    private CalculateTotalPriceCart calculateTotalPriceCart;

    @Mock
    private CalculatePricePerQuantity calculatePricePerQuantity;

    @Mock
    private FormatToBrazilianCurrency formatToBrazilianCurrency;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {
        Product product1 = new Product();
        product1.setPrice(100.0);

        Product product2 = new Product();
        product2.setPrice(200.0);

        Cart cartItem1 = new Cart();
        cartItem1.setProduct(product1);
        cartItem1.setQuantity(2);

        Cart cartItem2 = new Cart();
        cartItem2.setProduct(product2);
        cartItem2.setQuantity(1);

        List<Cart> cart = Arrays.asList(cartItem1, cartItem2);

        when(calculatePricePerQuantity.execute(2, 100.0)).thenReturn(200.0);
        when(calculatePricePerQuantity.execute(1, 200.0)).thenReturn(200.0);
        when(formatToBrazilianCurrency.execute(400.0)).thenReturn("R$ 400,00");

        String result = calculateTotalPriceCart.execute(cart);

        assertEquals("R$ 400,00", result);
    }
}
