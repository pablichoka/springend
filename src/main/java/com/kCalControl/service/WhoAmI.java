package com.kCalControl.service;

import com.kCalControl.model.User;

import java.util.Optional;

public interface WhoAmI {
    Optional<Integer> whoAmI();

    Optional<User> currentUser();
}
