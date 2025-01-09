package com.backend_ecommerce.backend_ecommerce.models.response;

import com.backend_ecommerce.backend_ecommerce.models.utils.Roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPayloadResponse {
    private String email;
    private String name;
    private Roles role;
}
