package com.backend_ecommerce.backend_ecommerce.services.user;

import org.springframework.stereotype.Service;

@Service
public class ValidateStrongPassword {
    
    public void execute(String password) {

        if(password.length() < 8) {
            throw new RuntimeException("Senha deve ter pelo menos 8 caracteres");
        }
        
        if(!password.matches(".*[A-Z].*")) {
            throw new RuntimeException("Senha deve ter pelo menos 1 letra maiúscula");
        }
        
        if(!password.matches(".*[a-z].*")) {
            throw new RuntimeException("Senha deve ter pelo menos 1 letra minúscula");
        }
        
        if(!password.matches(".*[0-9].*")) {
            throw new RuntimeException("Senha deve ter pelo menos 1 número");
        }
    }
}
