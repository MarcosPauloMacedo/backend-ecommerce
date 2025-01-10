package com.backend_ecommerce.backend_ecommerce.services.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.models.utils.OrderStatus;

@Service
public class ValidateStatusOrder {
    
    @Autowired
    private OrderService orderService;

    public void inSave(Long orderId) {
        var order = orderService.selectById(orderId);

        if (order.getStatus().equals(OrderStatus.CANCELLED)) {
            throw new RuntimeException("Não é possível adicionar itens a um pedido cancelado.");
        }
    }

    public void inUpdate(Long orderId) {
        var order = orderService.selectById(orderId);

        if (order.getStatus().equals(OrderStatus.CANCELLED)) {
            throw new RuntimeException("Não é possível atualizar itens de um pedido cancelado.");
        }
    }
}
