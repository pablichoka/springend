package com.kCalControl.controller;

import com.kCalControl.model.Role;
import com.kCalControl.model.UserDB;
import com.kCalControl.model.UserRole;
import com.kCalControl.repository.UserRepository;
import com.kCalControl.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<UserDB> userOptional = userRepository.findByUsername(name);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("");
        }
        UserDB userDB = userOptional.get();

        Optional<UserRole> userRole = userRoleRepository.findByUserDB(userDB);


        UserDetails user = User.builder().username(userDB.getFirstName()).password(userDB.getPassword()).roles(userRole.get().getRole().getRole())
                .build();
        logger.debug("User authenticated: {}", user);
        return user;
    }
}
