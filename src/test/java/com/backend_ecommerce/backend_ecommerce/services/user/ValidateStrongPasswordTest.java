package com.backend_ecommerce.backend_ecommerce.services.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidateStrongPasswordTest {

    private ValidateStrongPassword validateStrongPassword;

    @BeforeEach
    public void setUp() {
        validateStrongPassword = new ValidateStrongPassword();
    }

    @Test
    public void testExecute_ValidPassword() {
        String password = "Valid123";

        assertDoesNotThrow(() -> {
            validateStrongPassword.execute(password);
        });
    }

    @Test
    public void testExecute_PasswordTooShort() {
        String password = "Val1";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            validateStrongPassword.execute(password);
        });

        assertEquals("Senha deve ter pelo menos 8 caracteres", exception.getMessage());
    }

    @Test
    public void testExecute_NoUpperCaseLetter() {
        String password = "valid123";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            validateStrongPassword.execute(password);
        });

        assertEquals("Senha deve ter pelo menos 1 letra maiúscula", exception.getMessage());
    }

    @Test
    public void testExecute_NoLowerCaseLetter() {
        String password = "VALID123";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            validateStrongPassword.execute(password);
        });

        assertEquals("Senha deve ter pelo menos 1 letra minúscula", exception.getMessage());
    }

    @Test
    public void testExecute_NoNumber() {
        String password = "ValidPass";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            validateStrongPassword.execute(password);
        });

        assertEquals("Senha deve ter pelo menos 1 número", exception.getMessage());
    }
}
