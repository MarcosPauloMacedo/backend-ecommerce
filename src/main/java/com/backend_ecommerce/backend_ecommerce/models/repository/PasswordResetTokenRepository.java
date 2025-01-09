package com.backend_ecommerce.backend_ecommerce.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend_ecommerce.backend_ecommerce.models.entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
    PasswordResetToken findByEmail(String email);
}
