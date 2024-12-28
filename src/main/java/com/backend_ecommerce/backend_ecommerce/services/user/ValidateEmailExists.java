package com.backend_ecommerce.backend_ecommerce.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.models.entity.User;
import com.backend_ecommerce.backend_ecommerce.models.repository.UserRepository;

@Service
public class ValidateEmailExists {

    @Autowired
    private UserRepository userRepository;

    public void execute(String email) {
        boolean IsEmailExits = userRepository.findByEmail(email).isPresent();
        
        if(IsEmailExits) {
            throw new RuntimeException("Email já existe");
        }
    }

    public void execute(String email, Long id) {
        User user = userRepository.findById(id).get();
        
        if(!user.getEmail().equals(email)) {
            boolean IsEmailExits = userRepository.findByEmail(email).isPresent();
            
            if(IsEmailExits) {
                throw new RuntimeException("Email já existe");
            }
        }
    }
}
