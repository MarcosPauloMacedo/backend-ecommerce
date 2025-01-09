package com.backend_ecommerce.backend_ecommerce.models.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String email;
    private String token;
    private String password;    
}
