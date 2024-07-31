package com.jira.api_jira.services;

import com.jira.api_jira.models.User;

public interface UserService {
    User getUserById(Integer id);
    User updateUser(User user);
    User getUserByEmail(String email);
    User getUserByEmailAndPassword(String email, String password);
    User resetUserCredentials(String email);
}
