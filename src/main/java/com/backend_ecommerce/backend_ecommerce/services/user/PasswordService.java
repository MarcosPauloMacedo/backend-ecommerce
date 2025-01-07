package com.backend_ecommerce.backend_ecommerce.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    @Autowired
    PasswordEncoder passwordEncoder;
    
    public String generateHashPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
