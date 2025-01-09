package com.backend_ecommerce.backend_ecommerce.services.auth;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class GenerateTokenInPasswordReset {
    
    public String execute() {
        return UUID.randomUUID().toString().substring(0, 6);
    }
}
