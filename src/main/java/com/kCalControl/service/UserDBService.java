package com.kCalControl.service;

import com.kCalControl.dto.SearchParamsDTO;
import com.kCalControl.dto.user.NewUserDTO;
import com.kCalControl.dto.user.UpdatePasswordDTO;
import com.kCalControl.dto.user.UpdateUserDataDTO;
import com.kCalControl.model.UserDB;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;

public interface UserDBService {
    UserDB newAdminUser(ObjectId id, NewUserDTO dto);

    UserDB newNormalUser(NewUserDTO dto);

    UserDB returnUserById(Integer id);

//    UserDB returnLoggedUser();

//    String getUsernameLoggedUser();

    Page<UserDB> getUsers(int page, int pageSize);

    Page<UserDB> getUsersFromSearch(SearchParamsDTO dto);

    UserDB updateUserData(Integer id, UpdateUserDataDTO dto);

    UserDB updatePassword(Integer id, UpdatePasswordDTO dto);

    void deleteUser(Integer id);
}
