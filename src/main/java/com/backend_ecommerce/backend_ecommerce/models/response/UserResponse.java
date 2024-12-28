package com.backend_ecommerce.backend_ecommerce.models.response;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String role;
}
