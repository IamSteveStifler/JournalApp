package com.aman.Journal.App.service.impl;

import com.aman.Journal.App.entity.User;
import com.aman.Journal.App.repository.UserRepository;
import com.aman.Journal.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Boolean saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Boolean updateUserByUserName(String userName, User user) {
        User userInDB = userRepository.findByUserName(userName);
        if(userInDB == null) return false;
        userInDB.setUserName(user.getUserName());
        userInDB.setPassword(user.getPassword());
        userRepository.save(userInDB);
        return true;
    }

    @Override
    public Boolean deleteUserByUserName(String userName) {
        User userInDB = userRepository.findByUserName(userName);
        if(userInDB == null) return false;
        userRepository.delete(userInDB);
        return true;
    }
}
