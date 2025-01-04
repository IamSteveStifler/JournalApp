package com.aman.Journal.App.service.impl;

import com.aman.Journal.App.entity.User;
import com.aman.Journal.App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userInDB = userRepository.findByUserName(username);
        if(userInDB != null) {
           return org.springframework.security.core.userdetails.User
                    .builder()
                    .username(userInDB.getUserName())
                    .password(userInDB.getPassword())
                    .roles(userInDB.getRoles().toArray(new String[0]))
                    .build();
        }

        throw new UsernameNotFoundException("User doesn't exist with the username: "+username);
    }
}
