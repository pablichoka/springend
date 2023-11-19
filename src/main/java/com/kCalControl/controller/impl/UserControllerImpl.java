package com.kCalControl.controller.impl;

import com.kCalControl.config.Checker;
import com.kCalControl.controller.UserDBController;
import com.kCalControl.dto.SearchParamsDTO;
import com.kCalControl.dto.user.*;
import com.kCalControl.model.User;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.repository.UserDBRepository;
import com.kCalControl.exceptions.NetworkException;
import com.kCalControl.service.UserService;
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
    Checker checker;
    @Autowired
    BMDataRepository bmDataRepository;
    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> createNormalUser(NewUserDTO dto) {
        User newUser = userService.newUser(dto);
        bmDataRepository.save(newUser.getBmData());
        userDBRepository.save(newUser);

        return ResponseEntity.ok("User created successfully");
    }

    @Override
    public ResponseEntity<String> getUserData(Integer id) {
        if (checker.checkValidUser(id)) {
            throw new NetworkException("Valid user check failed", HttpStatus.FORBIDDEN);
        }
        User user = userService.returnUserById(id);
        RetrieveUserDTO retrieveUserDTO = new RetrieveUserDTO(user.getUsername(),
                user.getName(), user.getMobile(), user.getEmail());
        return ResponseEntity.ok(retrieveUserDTO.toJSON());
    }

    @Override
    public ResponseEntity<String> deleteUser(Integer id) {
        if (checker.checkValidUser(id)) {
            throw new NetworkException("Valid user check failed", HttpStatus.FORBIDDEN);
        }
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @Override
    public ResponseEntity<String> updateUserData(Integer id, UpdateUserDataDTO dto) {
        if (checker.checkValidUser(id)) {
            throw new NetworkException("Valid user check failed", HttpStatus.FORBIDDEN);
        }
        User updatedUser = userService.updateUserData(id, dto);
        userDBRepository.save(updatedUser);

        return ResponseEntity.ok("User data updated successfully");
    }

    @Override
    public ResponseEntity<String> updatePassword(Integer id, UpdatePasswordDTO dto) {
        if (checker.checkValidUser(id)) {
            throw new NetworkException("Valid user check failed", HttpStatus.FORBIDDEN);
        }
        User updatedUser = userService.updateCredentials(id, dto);
        userDBRepository.save(updatedUser);
        return ResponseEntity.ok("Password updated successfully");
    }

    @Override
    public ResponseEntity<RetrieveUsersDTO> getUsersList(SearchParamsDTO dto) {
        if (!checker.checkRoleAdmin()) {
            throw new NetworkException("Missing ADMIN role", HttpStatus.FORBIDDEN);
        }
        Page<User> usersList = userService.getUsers(dto.getPage(), dto.getPageSize());
        RetrieveUsersDTO response = new RetrieveUsersDTO(usersList.getNumberOfElements(), usersList.getContent().stream().map(RetrieveUserDTO::new).toList());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RetrieveUsersDTO> getUsersFromSearch(SearchParamsDTO dto) {
        if (!checker.checkRoleAdmin()) {
            throw new NetworkException("Missing ADMIN role", HttpStatus.FORBIDDEN);
        }
        Page<User> userSearchList = userService.getUsersFromSearch(dto);
        RetrieveUsersDTO response = new RetrieveUsersDTO(userSearchList.getNumberOfElements(), userSearchList.getContent().stream().map(RetrieveUserDTO::new).toList());
        return ResponseEntity.ok(response);
    }

}
