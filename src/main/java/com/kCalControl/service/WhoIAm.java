package com.kCalControl.service;

import com.kCalControl.model.UserDB;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface WhoIAm {
    Integer whoIAm();

    Optional<UserDB> currentUser();
}
