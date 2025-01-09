package com.backend_ecommerce.backend_ecommerce.models.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.backend_ecommerce.backend_ecommerce.models.utils.Roles;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @Override 
    public Collection<? extends  GrantedAuthority > getAuthorities() { 
        return List.of(); 
    } 

    public String getPassword () { 
        return password; 
    } 

    @Override 
    public String getUsername () { 
        return email; 
    } 

    @Override 
    public boolean isAccountNonExpired () { 
        return true ; 
    } 

    @Override 
    public boolean isAccountNonLocked () { 
        return true ; 
    } 

    @Override 
    public boolean isCredentialsNonExpired () { 
        return true ; 
    } 

    @Override 
    public boolean isEnabled () { 
        return true ; 
    } 
}
