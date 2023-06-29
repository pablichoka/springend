package com.kCalControl.service;

import com.kCalControl.dto.UpdatePasswordDTO;
import com.kCalControl.dto.UpdateUserDataDTO;
import com.kCalControl.model.UserDB;
import com.kCalControl.dto.NewUserDTO;
import com.kCalControl.dto.UpdatePersonalDataDTO;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

public interface UserDBService {
    UserDB newUser(ObjectId creationPersonId, NewUserDTO dto, @RequestParam("role") String role);

    UserDB returnUser(ObjectId id);

    UserDB updatePersonalData(ObjectId id, UpdatePersonalDataDTO dto, Principal principal);

    UserDB updateUserData(ObjectId id, UpdateUserDataDTO dto, Principal principal);

    UserDB updatePassword(ObjectId id, UpdatePasswordDTO dto, Principal principal);
}
