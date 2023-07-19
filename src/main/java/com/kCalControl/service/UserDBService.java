package com.kCalControl.service;

import com.kCalControl.dto.NewUserDTO;
import com.kCalControl.dto.UpdatePasswordDTO;
import com.kCalControl.dto.UpdatePersonalDataDTO;
import com.kCalControl.dto.UpdateUserDataDTO;
import com.kCalControl.model.UserDB;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

public interface UserDBService {
    UserDB newAdminUser(ObjectId creationPersonId, NewUserDTO dto, @RequestParam("role") String role);

    UserDB newNormalUser(NewUserDTO dto);

    UserDB returnUserById(ObjectId id);

    UserDB returnLoggedUser();

    String getUsernameLoggedUser();

    Page<UserDB> getUsers(int page, int pageSize);

    Page<UserDB> getUsersFromSearch(int page, int pageSize, String query, String filter, String sort);

    UserDB updateUserData(ObjectId id, UpdateUserDataDTO dto, Principal principal);

    UserDB updatePassword(ObjectId id, UpdatePasswordDTO dto, Principal principal);

    void deleteUser(ObjectId id);
}
