package com.backend_ecommerce.backend_ecommerce.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.exceptions.ResourceNotFoundException;
import com.backend_ecommerce.backend_ecommerce.interfaces.auth.DataServicePasswordResetToken;
import com.backend_ecommerce.backend_ecommerce.models.mapper.PasswordResetTokenMapper;
import com.backend_ecommerce.backend_ecommerce.models.repository.PasswordResetTokenRepository;
import com.backend_ecommerce.backend_ecommerce.models.request.PasswordResetTokenRequest;
import com.backend_ecommerce.backend_ecommerce.models.request.ResetPasswordRequest;
import com.backend_ecommerce.backend_ecommerce.services.shared.EmailService;
import com.backend_ecommerce.backend_ecommerce.services.user.UserService;

@Service
public class PasswordResetTokenService implements DataServicePasswordResetToken {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenServiceDate tokenServiceDate;

    @Autowired
    private PasswordResetTokenMapper passwordResetTokenMapper;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    public void requestPasswordReset(PasswordResetTokenRequest request) {
        var passwordResetToken = passwordResetTokenMapper.toEntity(request);
        passwordResetTokenRepository.save(passwordResetToken);

        emailService.sendPasswordResetEmail(
            request.getEmail(),
            passwordResetToken.getToken()
        );
    }

    public void resetPassword(ResetPasswordRequest request) {
        var passwordResetToken = passwordResetTokenRepository
        .findByToken(request.getToken());

        if (passwordResetToken == null) {
            throw new ResourceNotFoundException("Token inválido!");
        }

        if (!passwordResetToken.getEmail().equals(request.getEmail())) {
            throw new ResourceNotFoundException("Email inválido!");
        }

        boolean tokenExpired = tokenServiceDate.
        validateIfDateIsExpired(passwordResetToken.getExpiryDate());

        if(tokenExpired) {
            throw new ResourceNotFoundException("Token expirado!");
        }

        userService.updatePassword(request.getEmail(), request.getPassword());
        passwordResetTokenRepository.delete(passwordResetToken);
    }
}