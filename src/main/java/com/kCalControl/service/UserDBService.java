package com.kCalControl.service;

import com.kCalControl.service.dto.NewUserDTO;
import org.bson.types.ObjectId;

public interface UserDBService {
    ObjectId newUser(ObjectId creationPersonId, NewUserDTO dto);
}
