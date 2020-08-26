package com.example.petadmin.service;

import org.springframework.security.core.userdetails.UserDetails;

interface UsersecurityService {
    public UserDetails loadUserByUsername(String username);
}
