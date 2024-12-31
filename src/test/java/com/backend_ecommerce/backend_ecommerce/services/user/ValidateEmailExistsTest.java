package com.backend_ecommerce.backend_ecommerce.services.user;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.backend_ecommerce.backend_ecommerce.models.entity.User;
import com.backend_ecommerce.backend_ecommerce.models.repository.UserRepository;

import java.util.Optional;

public class ValidateEmailExistsTest {

    @InjectMocks
    private ValidateEmailExists validateEmailExists;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute_EmailExists() {
        String email = "test@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            validateEmailExists.execute(email);
        });

        assertEquals("Email já existe", exception.getMessage());
        verify(userRepository).findByEmail(email);
    }

    @Test
    public void testExecute_EmailDoesNotExist() {
        String email = "test@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> {
            validateEmailExists.execute(email);
        });

        verify(userRepository).findByEmail(email);
    }

    @Test
    public void testExecuteWithId_EmailExists() {
        Long id = 1L;
        String email = "test@example.com";
        User user = new User();
        user.setEmail("old@example.com");

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            validateEmailExists.execute(email, id);
        });

        assertEquals("Email já existe", exception.getMessage());
        verify(userRepository).findById(id);
        verify(userRepository).findByEmail(email);
    }

    @Test
    public void testExecuteWithId_EmailDoesNotExist() {
        Long id = 1L;
        String email = "test@example.com";
        User user = new User();
        user.setEmail("old@example.com");

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> {
            validateEmailExists.execute(email, id);
        });

        verify(userRepository).findById(id);
        verify(userRepository).findByEmail(email);
    }

    @Test
    public void testExecuteWithId_EmailUnchanged() {
        Long id = 1L;
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> {
            validateEmailExists.execute(email, id);
        });

        verify(userRepository).findById(id);
        verify(userRepository, never()).findByEmail(email);
    }
}
