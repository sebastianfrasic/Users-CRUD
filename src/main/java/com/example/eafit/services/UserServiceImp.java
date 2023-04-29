package com.example.eafit.services;

import com.example.eafit.model.User;
import com.example.eafit.model.exceptions.BusinessException;
import com.example.eafit.repositories.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepositoryImpl userRepositoryImpl;

    @Autowired
    public UserServiceImp(UserRepositoryImpl userRepositoryImpl) {
        this.userRepositoryImpl = userRepositoryImpl;
    }

    @Override
    public User createUser(User user) throws BusinessException {
        validateUserCreation(user);
        validateNotExisingSameEmail(user);
        return userRepositoryImpl.saveUser(user);

    }

    @Override
    public User getUserByID(String id) throws BusinessException {
        return Optional.ofNullable(userRepositoryImpl.getUserById(id))
                .orElseThrow(() -> new BusinessException("User does not exist"));
    }

    @Override
    public List<User> getUsersByName(String name) throws BusinessException {
        List<User> usersList = userRepositoryImpl.getUsersByName(name);
        if (usersList.isEmpty()) {
            throw new BusinessException("There are no users with that username");
        }
        return usersList;
    }

    @Override
    public List<User> getAllUsers() throws BusinessException {
        List<User> usersList = userRepositoryImpl.getAllUsersFromDatabase();
        if (usersList.isEmpty()) {
            throw new BusinessException("There are no users");
        }
        return usersList;
    }

    private void validateNotExisingSameEmail(User user) throws BusinessException {
        List<User> databaseUsers = userRepositoryImpl.getAllUsersFromDatabase();
        for (User databaseUser : databaseUsers) {
            if (databaseUser.getEmail().equalsIgnoreCase(user.getEmail())) {
                throw new BusinessException("Email already exists");
            }
        }
    }

    private void validateUserCreation(User user) throws BusinessException {
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new BusinessException("Username is not valid");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new BusinessException("Email is not valid");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new BusinessException("Password is not valid");
        }
        if (!user.getEmail().contains("@")) {
            throw new BusinessException("Invalid email");
        }
    }
}
