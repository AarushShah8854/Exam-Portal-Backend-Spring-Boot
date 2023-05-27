package com.exam.controller;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import com.exam.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController()
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        user.setProfile("default.png");

        //Encoding password using BCryptPasswordEncoder
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        Set<UserRole> userRoleSet = new HashSet<>();
        userRoleSet.add(userRole);

        return userService.createUser(user, userRoleSet);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return this.userService.getUser(username);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
    }
}
