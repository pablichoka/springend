package com.kCalControl.controller;

import com.kCalControl.model.UserDB;
import com.kCalControl.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSession implements UserDetailsService {
    private final static Logger logger = LoggerFactory.getLogger(UserSession.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<UserDB> userOptional = userRepository.findByUsername(name);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("");
        }
        UserDB userDB = userOptional.get();


        UserDetails user = User.builder().username(userDB.getUsername()).password(userDB.getPassword()).roles(userDB.getRole().getId())
                .build();
        logger.debug("User authenticated: {}", user);
        return user;
    }
}
