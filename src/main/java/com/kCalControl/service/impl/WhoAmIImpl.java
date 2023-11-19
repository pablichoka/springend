package com.kCalControl.service.impl;

import com.kCalControl.model.User;
import com.kCalControl.repository.UserRepository;
import com.kCalControl.service.WhoAmI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WhoAmIImpl implements WhoAmI {

    @Autowired
    UserRepository userRepository;

    @Override
    public Integer whoAmI() {
        try {
            return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Optional<User> currentUser() {
        return userRepository.findById(whoAmI());
    }
}
