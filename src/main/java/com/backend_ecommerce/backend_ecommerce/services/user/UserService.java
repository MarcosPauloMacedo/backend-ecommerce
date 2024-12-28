package com.backend_ecommerce.backend_ecommerce.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.interfaces.user.DataServiceUser;
import com.backend_ecommerce.backend_ecommerce.models.entity.User;
import com.backend_ecommerce.backend_ecommerce.models.mapper.UserMapper;
import com.backend_ecommerce.backend_ecommerce.models.repository.UserRepository;
import com.backend_ecommerce.backend_ecommerce.models.request.UserRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.UserResponse;

@Service
public class UserService implements DataServiceUser {
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidateEmailExists validateEmailExists;

    @Autowired
    private ValidateStrongPassword validateStrongPassword;

    @Override
    public UserResponse save(UserRequest request) {
        validateEmailExists.execute(request.getEmail());
        validateStrongPassword.execute(request.getPassword());
        
        User user = userMapper.toEntity(request);
        User userSave = userRepository.save(user);

        return userMapper.toResponse(userSave);
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {
        validateEmailExists.execute(request.getEmail(), id);
        validateStrongPassword.execute(request.getPassword());

        User user = userMapper.toEntity(id,request);
        User userUpdate = userRepository.save(user);

        return userMapper.toResponse(userUpdate);
    }

    @Override
    public UserResponse selectById(Long id) {
        User user = userRepository.findById(id).get();
        return userMapper.toResponse(user);
    }

    @Override
    public PageResponse<UserResponse> selectAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<User> users = userRepository.findAll(pageable);

        return userMapper.toResponsePage(users);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }
}
