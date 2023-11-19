package com.kCalControl.controller.impl;

import com.kCalControl.config.Checker;
import com.kCalControl.controller.UserController;
import com.kCalControl.dto.SearchParamsDTO;
import com.kCalControl.dto.credentials.UpdateCredentialsDTO;
import com.kCalControl.dto.user.*;
import com.kCalControl.model.User;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.repository.UserRepository;
import com.kCalControl.exceptions.NetworkException;
import com.kCalControl.service.UserService;
import com.kCalControl.service.WhoAmI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserControllerImpl implements UserController {

    private static final Logger logger = Logger.getLogger(UserControllerImpl.class.getName());
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    Checker checker;
    @Autowired
    BMDataRepository bmDataRepository;
    @Autowired
    UserService userService;
    @Autowired
    WhoAmI whoAmI;

    //TODO test if it saves the bmData
    @Override
    public ResponseEntity<String> createUser(NewUserDTO dto) {
        User newUser = userService.newUser(dto);
        userRepository.save(newUser);
        return ResponseEntity.ok("User created successfully %d".formatted(newUser.getId()));
    }

    @Override
    public ResponseEntity<RetrieveUserDTO> getUserData(Integer id) {
        if (!checker.checkGrantedUser(id)) {
            throw new NetworkException("Valid user check failed", HttpStatus.FORBIDDEN);
        }
        User user = userService.returnUserById(id);
        RetrieveUserDTO response = new RetrieveUserDTO(user.getUsername(),
                user.getName(), user.getMobile(), user.getEmail());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<String> deleteUser(Integer id) {
        if (!checker.checkGrantedUser(id)) {
            throw new NetworkException("Valid user check failed", HttpStatus.FORBIDDEN);
        }
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @Override
    public ResponseEntity<String> updateUserData(Integer id, UpdateUserDataDTO dto) {
        if (!checker.checkGrantedUser(id)) {
            throw new NetworkException("Valid user check failed", HttpStatus.FORBIDDEN);
        }
        User updatedUser = userService.updateUserData(id, dto);
        userRepository.save(updatedUser);

        return ResponseEntity.ok("User data updated successfully");
    }

    @Override
    public ResponseEntity<String> updatePassword(Integer id, UpdateCredentialsDTO dto) {
        if (!checker.checkGrantedUser(id)) {
            throw new NetworkException("Valid user check failed", HttpStatus.FORBIDDEN);
        }
        User updatedUser = userService.updateCredentials(id, dto);
        userRepository.save(updatedUser);
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
