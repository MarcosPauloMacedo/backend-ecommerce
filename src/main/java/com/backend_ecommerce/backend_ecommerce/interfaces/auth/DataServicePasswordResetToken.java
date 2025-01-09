package com.backend_ecommerce.backend_ecommerce.interfaces.auth;

import com.backend_ecommerce.backend_ecommerce.models.request.PasswordResetTokenRequest;
import com.backend_ecommerce.backend_ecommerce.models.request.ResetPasswordRequest;

public interface DataServicePasswordResetToken {
    void requestPasswordReset(PasswordResetTokenRequest request);
    void resetPassword(ResetPasswordRequest request);
}
