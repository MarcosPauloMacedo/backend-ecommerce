package com.backend_ecommerce.backend_ecommerce.services.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.backend_ecommerce.backend_ecommerce.models.entity.OrderItem;
import com.backend_ecommerce.backend_ecommerce.models.entity.Product;
import com.backend_ecommerce.backend_ecommerce.services.shared.CalculatePricePerQuantity;
import com.backend_ecommerce.backend_ecommerce.services.shared.FormatToBrazilianCurrency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CalculateTotalPriceOfOrderTest {

    @InjectMocks
    private CalculateTotalPriceOfOrder calculateTotalPriceOfOrder;

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

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProduct(product1);
        orderItem1.setQuantity(2);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProduct(product2);
        orderItem2.setQuantity(1);

        List<OrderItem> orderItems = Arrays.asList(orderItem1, orderItem2);

        when(calculatePricePerQuantity.execute(2, 100.0)).thenReturn(200.0);
        when(calculatePricePerQuantity.execute(1, 200.0)).thenReturn(200.0);
        when(formatToBrazilianCurrency.execute(400.0)).thenReturn("R$ 400,00");

        String result = calculateTotalPriceOfOrder.execute(orderItems);

        assertEquals("R$ 400,00", result);
    }
}
