package com.backend_ecommerce.backend_ecommerce.services.user;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.models.entity.User;
import com.backend_ecommerce.backend_ecommerce.models.repository.UserRepository;

@Service
public class ValidateEmail {
    
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    
    @Autowired
    private UserRepository userRepository;

    public void execute(String email) {
        isValidEmail(email);
        isEmailExits(email);
    }

    public void execute(String email, Long id) {
        isValidEmail(email);
        isEmailExits(email, id);
    }

    private void isValidEmail(String email) {
        boolean isValidEmail = EMAIL_PATTERN.matcher(email).matches();

        if(!isValidEmail) {
            throw new RuntimeException("Email inválido");
        }
    }

    private void isEmailExits(String email) {
        boolean IsEmailExits = userRepository.findByEmail(email).isPresent();
        
        if(IsEmailExits) {
            throw new RuntimeException("Email já existe");
        }
    }

    private void isEmailExits(String email, Long id) {
        User user = userRepository.findById(id).get();
        
        if(!user.getEmail().equals(email)) {
            boolean IsEmailExits = userRepository.findByEmail(email).isPresent();
            
            if(IsEmailExits) {
                throw new RuntimeException("Email já existe");
            }
        }
    }
}
