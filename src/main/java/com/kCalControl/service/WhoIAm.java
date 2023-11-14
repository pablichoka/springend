package com.kCalControl.service;

import com.kCalControl.model.User;

import java.util.Optional;

public interface WhoIAm {
    Integer whoIAm();

    Optional<User> currentUser();
}
