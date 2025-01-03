package com.backend_ecommerce.backend_ecommerce.services.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.exceptions.ResourceNotFoundException;
import com.backend_ecommerce.backend_ecommerce.models.repository.CartRepository;
import com.backend_ecommerce.backend_ecommerce.models.repository.OrderItemRepository;
import com.backend_ecommerce.backend_ecommerce.models.repository.OrderRepository;
import com.backend_ecommerce.backend_ecommerce.models.repository.ProductRepository;
import com.backend_ecommerce.backend_ecommerce.models.repository.UserRepository;

@Service
public class ValidateIfExistsById {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired OrderItemRepository orderItemRepository;

    public void inUser(Long id) {
        userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Não existe usuário com o id: " + id)
        );
    }

    public void inProduct(Long id) {
        productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Não existe produto com o id: " + id)
        );
    }

    public void inCart(Long id) {
        cartRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Não existe carrinho com o id: " + id)
        );
    }

    public void inOrder(Long id) {
        orderRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Não existe pedido com o id: " + id)
        );
    }

    public void inOrderItem(Long id) {
        orderItemRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Não existe item de pedido com o id: " + id)
        );
    }
}
