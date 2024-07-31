package com.jira.api_jira.services;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.api_jira.models.User;
import com.jira.api_jira.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> opUser = userRepository.findById(user.getId());
        if (!opUser.isPresent()) {
            return null;
        }
        user.setPassword(opUser.get().getPassword());
        user.setCreatedAt(opUser.get().getCreatedAt());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    @Override
    public User resetUserCredentials(String email) {
        Optional<User> opUser = userRepository.findByEmail(email);
        if (opUser.isPresent()) {
            User user = opUser.get();
            user.setPassword(opUser.get().getEmail());
            User updatedUser = userRepository.save(user);
            return updatedUser;
        } else {
            return null;
        }
    }
}
