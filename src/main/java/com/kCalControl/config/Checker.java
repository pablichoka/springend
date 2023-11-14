package com.kCalControl.config;

import com.kCalControl.model.Assets;
import com.kCalControl.model.Role;
import com.kCalControl.repository.RoleRepository;
import com.kCalControl.repository.UserDBRepository;
import com.kCalControl.service.WhoIAm;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Checker {

    private final static Logger logger = LoggerFactory.getLogger(Checker.class);
    @Autowired
    UserDBRepository userDBRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    WhoIAm whoIAm;

    public boolean checkRoleAdmin() {
        return whoIAm.currentUser()
                .map(user -> user.getRoles().contains(roleRepository.findById("ADMIN").get()))
                .orElse(false);
    }

    public boolean checkUserExistsById(Integer id) {
        return userDBRepository.findById(id).isPresent();
    }

    public boolean checkValidUser(Integer id) {
        if(checkRoleAdmin()){
            return true;
        }else{
            whoIAm.currentUser()
                    .map(user -> user.getId().equals(id));
            return false;
        }
    }
}
