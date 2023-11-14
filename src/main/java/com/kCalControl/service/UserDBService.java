package com.kCalControl.service;

import com.kCalControl.dto.SearchParamsDTO;
import com.kCalControl.dto.user.NewUserDTO;
import com.kCalControl.dto.user.UpdatePasswordDTO;
import com.kCalControl.dto.user.UpdateUserDataDTO;
import com.kCalControl.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;

public interface UserDBService {
    User newAdminUser(ObjectId id, NewUserDTO dto);

    User newNormalUser(NewUserDTO dto);

    User returnUserById(Integer id);

//    UserDB returnLoggedUser();

//    String getUsernameLoggedUser();

    Page<User> getUsers(int page, int pageSize);

    Page<User> getUsersFromSearch(SearchParamsDTO dto);

    User updateUserData(Integer id, UpdateUserDataDTO dto);

    User updatePassword(Integer id, UpdatePasswordDTO dto);

    void deleteUser(Integer id);
}
