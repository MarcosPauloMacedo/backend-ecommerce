package com.backend_ecommerce.backend_ecommerce.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.interfaces.user.DataServiceUser;
import com.backend_ecommerce.backend_ecommerce.models.entity.User;
import com.backend_ecommerce.backend_ecommerce.models.mapper.UserMapper;
import com.backend_ecommerce.backend_ecommerce.models.repository.UserRepository;
import com.backend_ecommerce.backend_ecommerce.models.request.UserRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.UserResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;
import com.backend_ecommerce.backend_ecommerce.services.shared.CreatePageable;
import com.backend_ecommerce.backend_ecommerce.services.shared.EmailService;
import com.backend_ecommerce.backend_ecommerce.services.shared.ValidateIfExistsById;

@Service
public class UserService implements DataServiceUser, UserDetailsService {
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreatePageable createPageable;

    @Autowired
    private ValidateIfExistsById validateIfExistsById;

    @Autowired
    private ValidateEmail validateEmail;

    @Autowired
    private ValidateStrongPassword validateStrongPassword;

    @Autowired
    private EmailService emailService;

    @Override
    public UserResponse save(UserRequest request) {
        validateEmail.execute(request.getEmail());
        validateStrongPassword.execute(request.getPassword());

        User user = userMapper.toEntity(request);
        User userSave = userRepository.save(user);

        emailService.sendWelcomeEmail(userSave.getEmail());

        return userMapper.toResponse(userSave);
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {
        validateIfExistsById.inUser(id);
        validateEmail.execute(request.getEmail(), id);
        validateStrongPassword.execute(request.getPassword());

        User user = userMapper.toEntity(id,request);
        User userUpdate = userRepository.save(user);

        return userMapper.toResponse(userUpdate);
    }

    @Override
    public UserResponse selectById(Long id) {
        validateIfExistsById.inUser(id);

        User user = userRepository.findById(id).get();

        return userMapper.toResponse(user);
    }

    @Override
    public PageResponse<UserResponse> selectAll(PageFilter pageFilter) {
        Pageable pageable = createPageable.execute(pageFilter);

        Page<User> users = userRepository.findAll(pageable);

        return userMapper.toResponsePage(users);
    }

    @Override
    public void delete(Long id) {
        validateIfExistsById.inUser(id);
        userRepository.deleteById(id);
    }

    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public Boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
