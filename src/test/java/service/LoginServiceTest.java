package com.revworkforce.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    private LoginService loginService = new LoginService();

    @Test
    void testInvalidLogin() {

        Exception exception = assertThrows(RuntimeException.class,
                () -> loginService.authenticate("wrong@gmail.com", "wrongpass"));

        assertTrue(exception.getMessage().contains("Invalid"));
    }
}
