package com.kCalControl.controller.impl;

import com.kCalControl.controller.AdminController;
import com.kCalControl.dto.creation.NewUserDTO;
import com.kCalControl.dto.search.SearchParamsDTO;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.AssetsRepository;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.repository.UserDBRepository;
import com.kCalControl.service.UserDBService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

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
