package com.kCalControl.service.impl;

import com.kCalControl.exceptions.NetworkException;
import com.kCalControl.model.User;
import com.kCalControl.repository.UserRepository;
import com.kCalControl.service.WhoAmI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WhoAmIImpl implements WhoAmI {

    private final static Logger logger = LoggerFactory.getLogger(WhoAmIImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public WhoAmIImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Integer whoAmI() {
        try {
            if (SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
                return null;
            } else {
                try {
                    return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName()); //returns credentials ID

                } catch (NetworkException e) {
                    throw new NetworkException("User not logged", HttpStatus.UNAUTHORIZED);
                }
//                logger.debug("Logged user credentials: " + SecurityContextHolder.getContext().getAuthentication().getName());
//                logger.debug("Credentials ID: " + userRepository.findByCredentialsId(credentials_id).map(User::getId).
//                        orElseThrow(() -> new NetworkException("User not exists", HttpStatus.NOT_FOUND)));
//                return userRepository.findByCredentialsId(credentials_id).map(User::getId);
            }
        } catch (NetworkException e) {
            throw new NetworkException("User not logged", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public Optional<User> currentUser() {
        if (whoAmI() != null) {
            return userRepository.findById(whoAmI());
        } else {
            return Optional.empty();
        }
    }
}
