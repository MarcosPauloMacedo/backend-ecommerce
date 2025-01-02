package com.backend_ecommerce.backend_ecommerce.services.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.models.entity.OrderItem;
import com.backend_ecommerce.backend_ecommerce.services.shared.CalculatePricePerQuantity;
import com.backend_ecommerce.backend_ecommerce.services.shared.FormatToBrazilianCurrency;

@Service
public class CalculateTotalPriceOfOrder {

    @Autowired
    private CalculatePricePerQuantity calculatePricePerQuantity;

    @Autowired
    private FormatToBrazilianCurrency formatToBrazilianCurrency;

    public String execute(List<OrderItem> orderItems) {
        Double totalPrice = 0.0;

        for (OrderItem item : orderItems) {
            totalPrice += calculatePricePerQuantity.execute(
                item.getQuantity(), 
                item.getProduct().getPrice()
            );
        }

        return formatToBrazilianCurrency.execute(totalPrice);
    }
    
}
