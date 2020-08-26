package com.example.petadmin.service.impl;

import com.example.petadmin.model.User;
import com.example.petadmin.repository.RoleRepository;
import com.example.petadmin.repository.UserRepository;
import com.example.petadmin.security.UserRole;
import com.example.petadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public
class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user, Set<UserRole> userRoles) {
        User localUser = userRepository.findByUsername(user.getUsername());
        if (localUser != null) {
            user.setId(localUser.getId());
            System.out.println("Username đã tồn tại");
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRole().addAll(userRoles);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            localUser = userRepository.save(user);
        }
        return localUser;
    }
}
