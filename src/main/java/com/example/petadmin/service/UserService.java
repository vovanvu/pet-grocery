package com.example.petadmin.service;

import com.example.petadmin.model.User;
import com.example.petadmin.security.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {
    public List<User> getAllUser();

    public User getUser(Long id);

    User findByUsername(String username);

    public User save(User user, Set<UserRole> userRoles);
}
