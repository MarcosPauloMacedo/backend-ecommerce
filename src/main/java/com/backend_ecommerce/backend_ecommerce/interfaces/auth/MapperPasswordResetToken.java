package com.backend_ecommerce.backend_ecommerce.interfaces.auth;

import com.backend_ecommerce.backend_ecommerce.models.entity.PasswordResetToken;
import com.backend_ecommerce.backend_ecommerce.models.request.PasswordResetTokenRequest;

public interface MapperPasswordResetToken {
    public PasswordResetToken toEntity(PasswordResetTokenRequest request);
}
