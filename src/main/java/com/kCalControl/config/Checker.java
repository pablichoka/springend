package com.kCalControl.config;

import com.kCalControl.repository.UserDBRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class Checker {

    @Autowired
    UserDBRepository userDBRepository;

    public boolean checkRoleAdminById(ObjectId id) {
        return userDBRepository.findById(id)
                .map(user -> user.getRoleName().equals("ADMIN"))
                .orElse(false);
    }

    public boolean checkUserExistsById(ObjectId id) {
        return userDBRepository.findById(id).isPresent();
    }

    //TODO implement a method which identifies the user by jwt
    public boolean checkSameUser(ObjectId id, ObjectId id2) {
        return userDBRepository.findById(id2)
                .map(user -> user.getId().equals(id))
                .orElse(false);
    }
}
