package com.kCalControl.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kCalControl.config.Checker;
import com.kCalControl.controller.UserDBController;
import com.kCalControl.dto.*;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.AssetsRepository;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.repository.UserDBRepository;
import com.kCalControl.service.BMDataService;
import com.kCalControl.service.UserDBService;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.json.JsonObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
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
    BMDataRepository bmDataRepository;
    @Autowired
    BMDataService bmDataService;
    @Autowired
    Checker checker;
    @Autowired
    UserDBService userDBService;

    @Override
    public void createAdminUser(@RequestParam("id") ObjectId id, @RequestParam("role") String role, NewUserDTO dto, Model model, HttpServletResponse response) {
        UserDB newUserDB = userDBService.newAdminUser(id, dto, role);
        bmDataRepository.save(newUserDB.getBmData());
        assetsRepository.save(newUserDB.getAssets());
        userDBRepository.save(newUserDB);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

//    @Override
//    public void createNormalUser(@RequestBody NewUserDTO dto) {
//        UserDB newUserDB = userDBService.newNormalUser(dto);
//        bmDataRepository.save(newUserDB.getBmData());
//        assetsRepository.save(newUserDB.getAssets());
//        userDBRepository.save(newUserDB);
//    }

    @Override
    public ResponseEntity<String> whoIAm() {
        String username = userDBService.getUsernameLoggedUser();
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode username2JSON = objectMapper.createObjectNode();
        username2JSON.put("username", username);
        return ResponseEntity.ok(username2JSON.toString());
    }

    @Override
    public String editUser(ObjectId id, Model model, Principal principal) {
        if (!checker.checkSameUser(principal, id, model)) {
            if (!checker.checkRoleAdminByPrincipal(principal, model)) {
                return "/noAuth/error/403";
            }
        }
        UserDB returnedUser = userDBService.returnUserById(id);
        model.addAttribute("user", returnedUser);
        model.addAttribute("userData", new UpdateUserDataDTO());
        model.addAttribute("personalData", new UpdatePersonalDataDTO());
        model.addAttribute("password", new UpdatePasswordDTO());
        return "/auth/api/editUser";
    }

    @Override
    public void deleteUser(ObjectId id, HttpServletResponse response) {
        userDBService.deleteUserFromAdmin(id);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @Override
    public void updateUserData(ObjectId id, UpdateUserDataDTO dto, Model model, HttpServletResponse response) {
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
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @Override
    public void updatePassword(ObjectId id, UpdatePasswordDTO dto, Model model, HttpServletResponse response) {
        UserDB moddedUser = userDBService.returnUserById(id);
        UserDB modificationUser = userDBService.returnLoggedUser();

        moddedUser.setPassword(passwordEncoder.encode(dto.getPassword()));

        moddedUser.setModificationPerson(modificationUser);
        moddedUser.setModificationDate(LocalDateTime.now());

        assetsRepository.save(moddedUser.getAssets());
        userDBRepository.save(moddedUser);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @Override
    public String getUsersList(int page, int pageSize, Model model) {
        Page<UserDB> usersList = userDBService.getUsers(page, pageSize);
        model.addAttribute("users", usersList.getContent());
        model.addAttribute("last", usersList.isLast());
        model.addAttribute("params", new SearchParamsDTO());
        return "/auth/admin/listUser";
    }

    @Override
    public String searchUsers(int page, int pageSize, SearchParamsDTO dto, Model model, HttpServletResponse response) {
        Page<UserDB> userSearchList = userDBService.getUsersFromSearch(page, pageSize, dto.getQuery(), dto.getFilter(), dto.getSort());
        model.addAttribute("users", userSearchList.getContent());
        model.addAttribute("params", new SearchParamsDTO());
        return "/auth/admin/listUser";
    }


}
