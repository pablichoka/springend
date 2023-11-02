package com.kCalControl.service.impl;

import com.kCalControl.model.UserDB;
import com.kCalControl.repository.UserDBRepository;
import com.kCalControl.service.WhoIAm;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WhoIAmImpl implements WhoIAm {

    @Autowired
    UserDBRepository userDBRepository;

    @Override
    public ObjectId whoIAm() {
        return new ObjectId(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public Optional<UserDB> currentUser() {
        return userDBRepository.findById(whoIAm());
    }
}
