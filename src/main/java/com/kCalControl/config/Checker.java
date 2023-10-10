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

    public boolean checkRoleAdminByPrincipal() {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return userDBRepository.findByUsername(currentUser)
                .map(user -> user.getRoleName().equals("ADMIN"))
                .orElse(false);
    }

    public boolean checkRoleAdminById(ObjectId id) {
        return userDBRepository.findById(id)
                .map(user -> user.getRoleName().equals("ADMIN"))
                .orElse(false);
    }

    public boolean checkUserExistsByPrincipal() {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return userDBRepository.findByUsername(currentUser).isPresent();
    }

    public boolean checkUserExistsById(ObjectId id) {
        return userDBRepository.findById(id).isPresent();
    }

    public boolean checkSameUser(ObjectId id) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return userDBRepository.findById(new ObjectId(currentUser))
                .map(user -> user.getId().equals(id))
                .orElse(false);
    }
}
