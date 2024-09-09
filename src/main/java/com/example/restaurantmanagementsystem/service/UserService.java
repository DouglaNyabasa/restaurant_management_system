package com.example.restaurantmanagementsystem.service;

import com.example.restaurantmanagementsystem.model.User;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;
}
