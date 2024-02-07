package com.kCalControl.service;

import com.kCalControl.model.User;

import java.util.Optional;

public interface WhoAmI {
    Integer whoAmI();

    Optional<User> currentUser();
}
