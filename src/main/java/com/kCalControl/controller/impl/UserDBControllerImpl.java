package com.kCalControl.controller.impl;

import com.kCalControl.config.Checker;
import com.kCalControl.controller.UserDBController;
import com.kCalControl.dto.SearchParamsDTO;
import com.kCalControl.dto.user.*;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.AssetsRepository;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.repository.UserDBRepository;
import com.kCalControl.exceptions.CustomException;
import com.kCalControl.service.UserDBService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserDBControllerImpl implements UserDBController {

    private static final Logger logger = Logger.getLogger(UserDBControllerImpl.class.getName());
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserDBRepository userDBRepository;
    @Autowired
    AssetsRepository assetsRepository;
    @Autowired
    Checker checker;
    @Autowired
    BMDataRepository bmDataRepository;
    @Autowired
    UserDBService userDBService;

    @Override
    public ResponseEntity<String> createNormalUser(NewUserDTO dto) {
        UserDB newUserDB = userDBService.newNormalUser(dto);
        bmDataRepository.save(newUserDB.getBmData());
        assetsRepository.save(newUserDB.getAssets());
        userDBRepository.save(newUserDB);

        return ResponseEntity.ok("User created successfully");
    }

    @Override
    public ResponseEntity<String> getUserData(ObjectId id) {
        if(!checker.checkValidUser(id)){
            return ResponseEntity.status(403).body("Valid user check failed");
        }
        UserDB userDB = userDBService.returnUserById(id);
        RetrieveUserDTO retrieveUserDTO = new RetrieveUserDTO(userDB.getUsername(),
                userDB.getFirstName(), userDB.getLastName(), userDB.getMobile(), userDB.getEmail());
        return ResponseEntity.ok(retrieveUserDTO.toJSON());
    }

    @Override
    public ResponseEntity<String> deleteUser(ObjectId id) {
        if(!checker.checkValidUser(id)){
            return ResponseEntity.status(403).body("Valid user check failed");
        }
        userDBService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @Override
    public ResponseEntity<String> updateUserData(ObjectId id, UpdateUserDataDTO dto) {
        if(!checker.checkValidUser(id)){
            return ResponseEntity.status(403).body("Valid user check failed");
        }
        UserDB updatedUser = userDBService.updateUserData(id, dto);
        assetsRepository.save(updatedUser.getAssets());
        userDBRepository.save(updatedUser);

        return ResponseEntity.ok("User data updated successfully");
    }

    @Override
    public ResponseEntity<String> updatePassword(ObjectId id, UpdatePasswordDTO dto) {
        if(!checker.checkValidUser(id)){
            return ResponseEntity.status(403).body("Valid user check failed");
        }
        UserDB updatedUser = userDBService.updatePassword(id, dto);
        assetsRepository.save(updatedUser.getAssets());
        userDBRepository.save(updatedUser);
        return ResponseEntity.ok("Password updated successfully");
    }

    @Override
    public ResponseEntity<RetrieveUsersDTO> getUsersList(SearchParamsDTO dto) {
        if(!checker.checkRoleAdmin()){
            throw new CustomException("Missing ADMIN role", HttpStatus.FORBIDDEN);
        }
        Page<UserDB> usersList = userDBService.getUsers(dto.getPage(), dto.getPageSize());
        RetrieveUsersDTO response = new RetrieveUsersDTO(usersList.getNumberOfElements(), usersList.getContent().stream().map(RetrieveUserDTO::new).toList());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RetrieveUsersDTO> getUsersFromSearch(SearchParamsDTO dto) {
        if(!checker.checkRoleAdmin()){
            return ResponseEntity.status(403).build();
        }
        Page<UserDB> userSearchList = userDBService.getUsersFromSearch(dto);
        RetrieveUsersDTO response = new RetrieveUsersDTO(userSearchList.getNumberOfElements(),userSearchList.getContent().stream().map(RetrieveUserDTO::new).toList());
        return ResponseEntity.ok(response);
    }
    
}
