package com.backend_ecommerce.backend_ecommerce.services.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculateAmountToPayPerItem {
    
    @Autowired
    private CalculatePricePerQuantity calculatePricePerQuantity;

    @Autowired
    private FormatToBrazilianCurrency formatToBrazilianCurrency;

    public String execute(Integer quantity, Double price) {
        Double amountToPay = calculatePricePerQuantity.execute(quantity, price);

        return formatToBrazilianCurrency.execute(amountToPay);
    }
}
