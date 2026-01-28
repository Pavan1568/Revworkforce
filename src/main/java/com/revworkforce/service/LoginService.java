package com.revworkforce.service;

import com.revworkforce.dao.LoginDAO;
import com.revworkforce.exception.LoginException;
import com.revworkforce.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginService {

    private static final Logger logger = LogManager.getLogger(LoginService.class);
    private LoginDAO loginDAO = new LoginDAO();

    public Employee authenticate(String email, String password) {

        Employee emp = loginDAO.login(email, password);

        if (emp == null) {
            logger.error("Login failed for email: " + email);
            throw new LoginException("Invalid email or password!");
        }

        logger.info("Login successful for user: " + email);
        return emp;
    }
}
