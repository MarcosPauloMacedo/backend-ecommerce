package com.backend_ecommerce.backend_ecommerce.services.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.models.entity.Cart;
import com.backend_ecommerce.backend_ecommerce.services.shared.CalculatePricePerQuantity;
import com.backend_ecommerce.backend_ecommerce.services.shared.FormatToBrazilianCurrency;

@Service
public class CalculateTotalPriceCart {

    @Autowired
    private CalculatePricePerQuantity calculatePricePerQuantity;

    @Autowired
    private FormatToBrazilianCurrency formatToBrazilianCurrency;

    String execute(List<Cart> cart) {
        Double totalPrice = 0.0;

        for (Cart c : cart) {
            totalPrice += calculatePricePerQuantity.execute(
                c.getQuantity(), 
                c.getProduct().getPrice()
            );
        }

        return formatToBrazilianCurrency.execute(totalPrice);
    }
}
