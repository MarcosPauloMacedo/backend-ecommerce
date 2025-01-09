package com.backend_ecommerce.backend_ecommerce.models.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
