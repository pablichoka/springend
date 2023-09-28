package com.kCalControl.controller.impl;

import com.kCalControl.controller.AdminController;
import com.kCalControl.dto.user.NewUserDTO;
import com.kCalControl.dto.SearchParamsDTO;
import com.kCalControl.dto.user.RetrieveUserDTO;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.AssetsRepository;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.repository.UserDBRepository;
import com.kCalControl.service.UserDBService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public class AdminControllerImpl implements AdminController {

    @Autowired
    UserDBRepository userDBRepository;
    @Autowired
    BMDataRepository bmDataRepository;
    @Autowired
    AssetsRepository assetsRepository;
    @Autowired
    UserDBService userDBService;

    @Override
    public ResponseEntity<Void> createAdminUser(NewUserDTO dto) {
        UserDB newUserDB = userDBService.newAdminUser(dto);
        bmDataRepository.save(newUserDB.getBmData());
        assetsRepository.save(newUserDB.getAssets());
        userDBRepository.save(newUserDB);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<String> getUserData(ObjectId id) {
        UserDB userDB = userDBService.returnUserById(id);
        RetrieveUserDTO retrieveUserDTO = new RetrieveUserDTO(userDB.getUsername(),
                userDB.getFirstName(), userDB.getLastName(), userDB.getMobile(), userDB.getEmail());
        return ResponseEntity.ok(retrieveUserDTO.toJSON());
    }

    @Override
    public String getUsersList(int page, int pageSize) {
        Page<UserDB> usersList = userDBService.getUsers(page, pageSize);
        return "/auth/admin/listUser";
    }

    @Override
    public String searchUsers(int page, int pageSize, SearchParamsDTO dto) {
        Page<UserDB> userSearchList = userDBService.getUsersFromSearch(page, pageSize, dto.getQuery(), dto.getFilter(), dto.getSort());
        return "/auth/admin/listUser";
    }
}
