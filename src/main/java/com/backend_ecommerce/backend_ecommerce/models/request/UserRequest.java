package com.backend_ecommerce.backend_ecommerce.models.request;


import com.backend_ecommerce.backend_ecommerce.models.utils.Roles;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private Roles role;
}