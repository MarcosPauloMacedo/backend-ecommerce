package com.backend_ecommerce.backend_ecommerce.models.mapper;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend_ecommerce.backend_ecommerce.interfaces.auth.MapperPasswordResetToken;
import com.backend_ecommerce.backend_ecommerce.models.entity.PasswordResetToken;
import com.backend_ecommerce.backend_ecommerce.models.request.PasswordResetTokenRequest;
import com.backend_ecommerce.backend_ecommerce.services.auth.TokenServiceDate;
import com.backend_ecommerce.backend_ecommerce.services.auth.GenerateTokenInPasswordReset;

@Component
public class PasswordResetTokenMapper implements MapperPasswordResetToken {

    @Autowired
    private GenerateTokenInPasswordReset generateTokenInPasswordReset;

    @Autowired
    private TokenServiceDate tokenServiceDate;

    @Override
    public PasswordResetToken toEntity(PasswordResetTokenRequest request) {
        String token = generateTokenInPasswordReset.execute();
        Date expiryDate = tokenServiceDate.createExpiryDate();

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        
        passwordResetToken.setEmail(request.getEmail());
        passwordResetToken.setToken(token);
        passwordResetToken.setExpiryDate(expiryDate);

        return passwordResetToken;
    }
    
}
