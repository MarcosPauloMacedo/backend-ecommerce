package com.backend_ecommerce.backend_ecommerce.models.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.backend_ecommerce.backend_ecommerce.interfaces.user.MapperUser;
import com.backend_ecommerce.backend_ecommerce.models.entity.User;
import com.backend_ecommerce.backend_ecommerce.models.request.UserRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.UserResponse;
import com.backend_ecommerce.backend_ecommerce.services.user.PasswordService;

@Component
public class UserMapper implements MapperUser {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordService passwordService;

    @Override
    public User toEntity(UserRequest request) {
        String hashPassword = passwordService.generateHashPassword(
            request.getPassword()
        );
        
        request.setPassword(hashPassword);
        return modelMapper.map(request, User.class);
    }

    @Override
    public User toEntity(Long id, UserRequest request) {
        User user = this.toEntity(request);
        user.setId(id);

        return user;
    }

    @Override
    public UserResponse toResponse(User entity) {
        return modelMapper.map(entity, UserResponse.class);
    }

    @Override
    public List<UserResponse> toResponseList(List<User> entities) {
        return entities.stream().map(entity -> this.toResponse(entity)).toList();
    }

    @Override
    public PageResponse<UserResponse> toResponsePage(Page<User> entities) {
        PageResponse<UserResponse> pageResponse = new PageResponse<>();
        pageResponse.setContent(this.toResponseList(entities.getContent()));
        pageResponse.setTotalPages(entities.getTotalPages());

        return pageResponse;
    }

}
