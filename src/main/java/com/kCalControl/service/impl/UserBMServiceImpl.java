package com.kCalControl.service.impl;

import com.kCalControl.model.UserBM;
import com.kCalControl.repository.UserBMRepository;
import com.kCalControl.service.UserBMService;
import com.kCalControl.service.UserDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBMServiceImpl implements UserBMService {
    @Autowired
    UserBMRepository userBMRepository;
    @Autowired
    UserDBService userDBService;
    @Override
    public UserBM returnBMLoggedUser() {
        return userBMRepository.findByUserAssoc_Id(userDBService.returnLoggedUser().getId());
    }
}
