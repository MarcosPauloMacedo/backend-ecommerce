package com.backend_ecommerce.backend_ecommerce.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import com.backend_ecommerce.backend_ecommerce.models.utils.OrderStatus;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime createAt;
}
