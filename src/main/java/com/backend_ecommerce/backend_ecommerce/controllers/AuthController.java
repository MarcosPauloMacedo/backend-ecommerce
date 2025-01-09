package com.backend_ecommerce.backend_ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.backend_ecommerce.backend_ecommerce.models.request.AuthRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.AuthResponse;
import com.backend_ecommerce.backend_ecommerce.services.auth.JwtService;
import com.backend_ecommerce.backend_ecommerce.services.user.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) throws AuthenticationException {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        final UserDetails userDetails = userService.loadUserByUsername(authRequest.getEmail());
        final String token = jwtService.generateToken(userDetails.getUsername());

        return new AuthResponse(token);
    }
}
