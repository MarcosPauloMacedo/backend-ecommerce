package com.backend_ecommerce.backend_ecommerce.services.shared;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class FormatToBrazilianCurrency {

    public String execute(Double totalPrice) {
        NumberFormat currencyFormat = NumberFormat
        .getCurrencyInstance(new Locale("pt", "BR"));

        return currencyFormat.format(totalPrice).replace('\u00A0', ' ');
    }
}
