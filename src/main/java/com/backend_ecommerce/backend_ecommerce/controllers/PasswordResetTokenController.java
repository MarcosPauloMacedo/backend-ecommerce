package com.backend_ecommerce.backend_ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend_ecommerce.backend_ecommerce.models.request.PasswordResetTokenRequest;
import com.backend_ecommerce.backend_ecommerce.models.request.ResetPasswordRequest;
import com.backend_ecommerce.backend_ecommerce.services.auth.PasswordResetTokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/password-reset-token")
@Tag(name = "Redefinição de senha", description = "Gerenciamento de redefinição de senha")
public class PasswordResetTokenController {

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @PostMapping()
    @Operation(summary = "Solicitar redefinição de senha")
    public void requestPasswordReset(@RequestBody PasswordResetTokenRequest request) {
        passwordResetTokenService.requestPasswordReset(request);
    }

    @PostMapping("/reset")
    @Operation(summary = "Redefinir senha")
    public void resetPassword(@RequestBody ResetPasswordRequest request) {
        passwordResetTokenService.resetPassword(request);
    }
}
