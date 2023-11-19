package com.kCalControl.service;

import com.kCalControl.dto.SearchParamsDTO;
import com.kCalControl.dto.credentials.NewCredentialsDTO;
import com.kCalControl.dto.user.NewUserDTO;
import com.kCalControl.dto.user.UpdatePasswordDTO;
import com.kCalControl.dto.user.UpdateUserDataDTO;
import com.kCalControl.model.Credentials;
import com.kCalControl.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;

public interface UserDBService {
    User newUser(NewUserDTO dto);

    Credentials newCredentials(NewCredentialsDTO dto);

    User returnUserById(Integer id);

    Page<User> getUsers(int page, int pageSize);

    Page<User> getUsersFromSearch(SearchParamsDTO dto);

    User updateUserData(Integer id, UpdateUserDataDTO dto);

    User updateCredentials(Integer id, UpdatePasswordDTO dto);

    void deleteUser(Integer id);
}
