package com.backend_ecommerce.backend_ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backend_ecommerce.backend_ecommerce.models.request.UserRequest;
import com.backend_ecommerce.backend_ecommerce.models.utils.Roles;
import com.backend_ecommerce.backend_ecommerce.services.user.UserService;

@Configuration
public class UserInitializer {

    @Autowired
    private UserService userService;

    @Bean
    CommandLineRunner init() {
        return args -> {
            if (userService.existsByEmail("admin@example.com")) {
                return;
            }

            UserRequest user = new UserRequest();
            user.setEmail("admin@example.com");
            user.setPassword("Senha,123");
            user.setName("Admin");
            user.setRole(Roles.ADMIN);

            userService.save(user);
        };
    }
} 
