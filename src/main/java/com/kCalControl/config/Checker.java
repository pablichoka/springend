package com.kCalControl.config;

import com.kCalControl.repository.RoleRepository;
import com.kCalControl.repository.UserRepository;
import com.kCalControl.service.WhoAmI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Checker {

    private final static Logger logger = LoggerFactory.getLogger(Checker.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    WhoAmI whoAmI;

    public boolean checkRoleAdmin() {
        return whoAmI.currentUser()
                .map(user -> user.getRoles().contains(roleRepository.findByRoleName("ADMIN").get()))
                .orElse(false);
    }

    public boolean checkUserExistsById(Integer id) {
        return userRepository.findById(id).isPresent();
    }

    public boolean checkGrantedUser(Integer id) {
        if(checkRoleAdmin()){
            return true;
        }else{
            whoAmI.currentUser()
                    .map(user -> user.getId().equals(id));
            return false;
        }
    }
}
