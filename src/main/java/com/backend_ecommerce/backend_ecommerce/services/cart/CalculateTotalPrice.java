package com.backend_ecommerce.backend_ecommerce.services.cart;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.models.entity.Cart;

@Service
public class CalculateTotalPrice {

    String execute(List<Cart> cart) {
        Double totalPrice = 0.0;

        for (Cart c : cart) {
            totalPrice += c.getProduct().getPrice() * c.getQuantity();
        }

        return formatToBrazilianCurrency(totalPrice);
    }

    private String formatToBrazilianCurrency(Double totalPrice) {
        NumberFormat currencyFormat = NumberFormat
        .getCurrencyInstance(new Locale("pt", "BR"));

        return currencyFormat.format(totalPrice).replace('\u00A0', ' ');
    }
}
