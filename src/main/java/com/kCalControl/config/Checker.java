package com.kCalControl.config;

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
    WhoIAm whoIAm;

    public boolean checkRoleAdminById() {
        return userDBRepository.findById(whoIAm.whoIAm())
                .map(user -> user.getRoleName().equals("ADMIN"))
                .orElse(false);
    }

    public boolean checkUserExistsById(ObjectId id) {
        return userDBRepository.findById(id).isPresent();
    }

    public boolean checkValidUser(ObjectId id) {
        if(checkRoleAdminById()){
            return true;
        }else{
            return userDBRepository.findById(whoIAm.whoIAm())
                    .map(user -> user.getId().equals(id))
                    .orElse(false);
        }
    }
}
