package com.kCalControl.service.impl;

import com.kCalControl.model.User;
import com.kCalControl.repository.UserDBRepository;
import com.kCalControl.service.WhoIAm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WhoIAmImpl implements WhoIAm {

    @Autowired
    UserDBRepository userDBRepository;

    @Override
    public Integer whoIAm() {
        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public Optional<User> currentUser() {
        return userDBRepository.findById(whoIAm());
    }
}
