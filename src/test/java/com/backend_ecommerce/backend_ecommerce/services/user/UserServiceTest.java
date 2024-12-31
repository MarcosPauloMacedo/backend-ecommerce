package com.backend_ecommerce.backend_ecommerce.services.user;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.backend_ecommerce.backend_ecommerce.models.entity.User;
import com.backend_ecommerce.backend_ecommerce.models.mapper.UserMapper;
import com.backend_ecommerce.backend_ecommerce.models.repository.UserRepository;
import com.backend_ecommerce.backend_ecommerce.models.request.UserRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.UserResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;
import com.backend_ecommerce.backend_ecommerce.services.shared.CreatePageable;

import java.util.Optional;
import java.util.Collections;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CreatePageable createPageable;

    @Mock
    private ValidateEmailExists validateEmailExists;

    @Mock
    private ValidateStrongPassword validateStrongPassword;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        UserRequest request = new UserRequest();
        User user = new User();
        User savedUser = new User();
        UserResponse response = new UserResponse();

        doNothing().when(validateEmailExists).execute(request.getEmail());
        doNothing().when(validateStrongPassword).execute(request.getPassword());
        when(userMapper.toEntity(request)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.toResponse(savedUser)).thenReturn(response);

        UserResponse result = userService.save(request);

        assertEquals(response, result);
        verify(validateEmailExists).execute(request.getEmail());
        verify(validateStrongPassword).execute(request.getPassword());
        verify(userMapper).toEntity(request);
        verify(userRepository).save(user);
        verify(userMapper).toResponse(savedUser);
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        UserRequest request = new UserRequest();
        User user = new User();
        User updatedUser = new User();
        UserResponse response = new UserResponse();

        doNothing().when(validateEmailExists).execute(request.getEmail(), id);
        doNothing().when(validateStrongPassword).execute(request.getPassword());
        when(userMapper.toEntity(id, request)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(updatedUser);
        when(userMapper.toResponse(updatedUser)).thenReturn(response);

        UserResponse result = userService.update(id, request);

        assertEquals(response, result);
        verify(validateEmailExists).execute(request.getEmail(), id);
        verify(validateStrongPassword).execute(request.getPassword());
        verify(userMapper).toEntity(id, request);
        verify(userRepository).save(user);
        verify(userMapper).toResponse(updatedUser);
    }

    @Test
    public void testSelectById() {
        Long id = 1L;
        User user = new User();
        UserResponse response = new UserResponse();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userMapper.toResponse(user)).thenReturn(response);

        UserResponse result = userService.selectById(id);

        assertEquals(response, result);
        verify(userRepository).findById(id);
        verify(userMapper).toResponse(user);
    }

    @Test
    public void testSelectAll() {
        PageFilter pageFilter = new PageFilter();
        Pageable pageable = mock(Pageable.class);
        Page<User> users = new PageImpl<>(Collections.emptyList());
        PageResponse<UserResponse> response = new PageResponse<>();

        when(createPageable.execute(pageFilter)).thenReturn(pageable);
        when(userRepository.findAll(pageable)).thenReturn(users);
        when(userMapper.toResponsePage(users)).thenReturn(response);

        PageResponse<UserResponse> result = userService.selectAll(pageFilter);

        assertEquals(response, result);
        verify(createPageable).execute(pageFilter);
        verify(userRepository).findAll(pageable);
        verify(userMapper).toResponsePage(users);
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        User user = new User();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        userService.delete(id);

        verify(userRepository).findById(id);
        verify(userRepository).delete(user);
    }
}
