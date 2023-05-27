package com.exam.service.impl;

import com.exam.helper.UserFoundException;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //Method implementation to create a new User
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userRepository.findByUsername(user.getUsername());
        if(local!=null) {
            System.out.println("Username already exists");
            throw new UserFoundException();
        } else {
            for(UserRole userRole: userRoles) {
                this.roleRepository.save(userRole.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }

        return local;
    }

    //Method to get User
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
