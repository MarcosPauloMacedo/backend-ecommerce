package com.backend_ecommerce.backend_ecommerce.services.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CalculateAmountToPayPerItemTest {

    @InjectMocks
    private CalculateAmountToPayPerItem calculateAmountToPayPerItem;

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
        Integer quantity = 3;
        Double price = 50.0;

        when(calculatePricePerQuantity.execute(quantity, price)).thenReturn(150.0);
        when(formatToBrazilianCurrency.execute(150.0)).thenReturn("R$ 150,00");

        String result = calculateAmountToPayPerItem.execute(quantity, price);

        assertEquals("R$ 150,00", result);
    }
}
