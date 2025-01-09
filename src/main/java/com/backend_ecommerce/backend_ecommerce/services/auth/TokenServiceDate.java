package com.backend_ecommerce.backend_ecommerce.services.auth;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class TokenServiceDate {

    private long time = 3600000;

    public Date createExpiryDate() {
        return new Date(System.currentTimeMillis() + time);
    }

    public boolean validateIfDateIsExpired(Date date) {
        return date.before(new Date());
    }
}
