package com.jira.api_jira.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jira.api_jira.models.User;
import com.jira.api_jira.request.LoginRequest;
import com.jira.api_jira.services.UserService;
import com.jira.api_jira.utils.ErrorApi;
import com.jira.api_jira.utils.ResponseApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * This method is called for a POST request
     * Url: localhost:8080/api/v1/login
     * Purpose: Consulting a user by email and password
     * 
     * @return Register user
     * @throws Exception
     */
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest request) throws Exception {
        try {
            User user = userService.getUserByEmailAndPassword(request.getEmail(), request.getPassword());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorApi(
                        false,
                        404,
                        "Credenciales incorrectas"));
            }
            ResponseApi<User> response = new ResponseApi<>(
                    "Success",
                    user,
                    true,
                    200);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * This method is called for a POST request
     * Url: localhost:8080/api/v1/auth/reset-credentials
     * Purpose: Reset user credentials
     * @return Register user
     * @throws Exception
     */
    @PostMapping("/reset-credentials")
    public ResponseEntity<Object> resetCredentialsUser(@RequestBody LoginRequest request) throws Exception {
        try {
            User user = userService.resetUserCredentials(request.getEmail());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorApi(
                        false,
                        404,
                        "Usuario no encontrado"));
            }
            ResponseApi<User> response = new ResponseApi<>(
                    "Success",
                    user,
                    true,
                    200);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}