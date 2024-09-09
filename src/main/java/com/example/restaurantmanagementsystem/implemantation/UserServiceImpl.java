package com.example.restaurantmanagementsystem.implemantation;

import com.example.restaurantmanagementsystem.appConfiguration.JwtProvider;
import com.example.restaurantmanagementsystem.model.User;
import com.example.restaurantmanagementsystem.repository.UserRepository;
import com.example.restaurantmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if (user == null){
            throw  new Exception("user not found");
        }
        return user;
    }
}
