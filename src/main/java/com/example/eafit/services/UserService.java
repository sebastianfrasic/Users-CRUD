package com.example.eafit.services;

import com.example.eafit.model.User;
import com.example.eafit.model.exceptions.BusinessException;

import java.util.List;

public interface UserService {

    User createUser(User user) throws BusinessException;

    User getUserByID(String id) throws BusinessException;

    List<User> getUsersByName(String name) throws BusinessException;

    List<User> getAllUsers() throws BusinessException;
}
