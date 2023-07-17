package com.kCalControl.service;

import com.kCalControl.model.UserBM;

public interface UserBMService {
    UserBM returnBMLoggedUser();

    Double calculateBaseBM();

    Double calculateFinalBM(String dietType, Integer numDaysEx);
}
