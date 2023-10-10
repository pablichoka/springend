package com.kCalControl.controller.impl;

import com.kCalControl.controller.AdminController;
import com.kCalControl.dto.user.*;
import com.kCalControl.dto.SearchParamsDTO;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.AssetsRepository;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.repository.UserDBRepository;
import com.kCalControl.service.UserDBService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminControllerImpl implements AdminController {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserDBRepository userDBRepository;
    @Autowired
    BMDataRepository bmDataRepository;
    @Autowired
    AssetsRepository assetsRepository;
    @Autowired
    UserDBService userDBService;

    @Override
    public ResponseEntity<String> createAdminUser(NewUserDTO dto) {
        UserDB newUserDB = userDBService.newAdminUser(dto);
        bmDataRepository.save(newUserDB.getBmData());
        assetsRepository.save(newUserDB.getAssets());
        userDBRepository.save(newUserDB);

        return ResponseEntity.ok("User created successfully from ADMIN");
    }

    @Override
    public ResponseEntity<RetrieveUserDTO> getUserData(ObjectId id) {
        UserDB userDB = userDBService.returnUserById(id);
        RetrieveUserDTO retrieveUserDTO = new RetrieveUserDTO(userDB.getUsername(),
                userDB.getFirstName(), userDB.getLastName(), userDB.getMobile(), userDB.getEmail());
        return ResponseEntity.ok(retrieveUserDTO);
    }

    @Override
    public ResponseEntity<String> updateUserData(ObjectId id, UpdateUserDataDTO dto) {
        UserDB moddedUser = userDBService.returnUserById(id);
        UserDB modificationUser = userDBService.returnLoggedUser();

        moddedUser.setFirstName(dto.getFirstName());
        moddedUser.setLastName(dto.getLastName());
        moddedUser.setMobile(dto.getMobile());
        moddedUser.setEmail(dto.getEmail());
        moddedUser.setModificationPerson(modificationUser);
        moddedUser.setModificationDate(LocalDateTime.now());

        assetsRepository.save(moddedUser.getAssets());
        userDBRepository.save(moddedUser);

        return ResponseEntity.ok("User data updated successfully from ADMIN");
    }

    @Override
    public ResponseEntity<String> updatePassword(ObjectId id, UpdatePasswordDTO dto) {
        UserDB moddedUser = userDBService.returnUserById(id);
        UserDB modificationUser = userDBService.returnLoggedUser();

        moddedUser.setPassword(passwordEncoder.encode(dto.getPassword()));

        moddedUser.setModificationPerson(modificationUser);
        moddedUser.setModificationDate(LocalDateTime.now());

        assetsRepository.save(moddedUser.getAssets());
        userDBRepository.save(moddedUser);
        return ResponseEntity.ok("Password updated successfully from ADMIN");
    }

    @Override
    public ResponseEntity<String> deleteUser(ObjectId id) {
        userDBService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully from ADMIN");
    }

    @Override
    public ResponseEntity<RetrieveUsersDTO> getUsersList(SearchParamsDTO dto) {
        Page<UserDB> usersList = userDBService.getUsers(dto.getPage(), dto.getPageSize());
        RetrieveUsersDTO response = new RetrieveUsersDTO(usersList.getNumberOfElements(), usersList.getContent().stream().map(RetrieveUserDTO::new).toList());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RetrieveUsersDTO> getUsersFromSearch(SearchParamsDTO dto) {
        Page<UserDB> userSearchList = userDBService.getUsersFromSearch(dto);
        RetrieveUsersDTO response = new RetrieveUsersDTO(userSearchList.getNumberOfElements(),userSearchList.getContent().stream().map(RetrieveUserDTO::new).toList());
        return ResponseEntity.ok(response);
    }
}
