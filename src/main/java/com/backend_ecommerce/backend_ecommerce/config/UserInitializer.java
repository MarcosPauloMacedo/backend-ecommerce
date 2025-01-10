package com.backend_ecommerce.backend_ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backend_ecommerce.backend_ecommerce.models.request.UserRequest;
import com.backend_ecommerce.backend_ecommerce.models.utils.Roles;
import com.backend_ecommerce.backend_ecommerce.services.user.UserService;

@Configuration
public class UserInitializer {

    @Value("${USER_INITIALIZER_EMAIL}")
    private String userInitializerEmail;

    @Value("${USER_INITIALIZER_PASSWORD}")
    private String userInitializerPassword;

    @Autowired
    private UserService userService;

    @Bean
    CommandLineRunner init() {
        return args -> {
            if (userService.existsByEmail(userInitializerEmail)) {
                return;
            }

            UserRequest user = new UserRequest();
            user.setEmail(userInitializerEmail);
            user.setPassword(userInitializerPassword);
            user.setName("Admin");
            user.setRole(Roles.ROLE_ADMIN);

            userService.save(user);
        };
    }
} 
