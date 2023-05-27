package com.exam.service;

import com.exam.model.User;
import com.exam.model.UserRole;

import java.util.Set;

public interface UserService {

    //Method to create a new User
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //Method to get User
    public User getUser(String username);

    //Method to delete User
    public void deleteUser(Long userId);
}
