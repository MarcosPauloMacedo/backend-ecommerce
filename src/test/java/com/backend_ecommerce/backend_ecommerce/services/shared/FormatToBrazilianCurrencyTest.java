package com.backend_ecommerce.backend_ecommerce.services.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FormatToBrazilianCurrencyTest {

    private FormatToBrazilianCurrency formatToBrazilianCurrency;

    @BeforeEach
    void setUp() {
        formatToBrazilianCurrency = new FormatToBrazilianCurrency();
    }

    @Test
    void testExecute() {
        Double totalPrice = 1234.56;
        String result = formatToBrazilianCurrency.execute(totalPrice);
        assertEquals("R$ 1.234,56", result);
    }
}
