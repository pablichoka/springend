package com.kCalControl.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kCalControl.config.Checker;
import com.kCalControl.controller.UserDBController;
import com.kCalControl.dto.user.NewUserDTO;
import com.kCalControl.dto.user.RetrieveUserDTO;
import com.kCalControl.dto.user.UpdatePasswordDTO;
import com.kCalControl.dto.user.UpdateUserDataDTO;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.AssetsRepository;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.repository.UserDBRepository;
import com.kCalControl.service.UserDBService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    Checker checker;
    @Autowired
    BMDataRepository bmDataRepository;
    @Autowired
    UserDBService userDBService;

    @Override
    public ResponseEntity<Void> createNormalUser(NewUserDTO dto) {
        UserDB newUserDB = userDBService.newNormalUser(dto);
        bmDataRepository.save(newUserDB.getBmData());
        assetsRepository.save(newUserDB.getAssets());
        userDBRepository.save(newUserDB);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<String> whoIAm() {
        String username = userDBService.getUsernameLoggedUser();
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode username2JSON = objectMapper.createObjectNode();
        username2JSON.put("username", username);
        return ResponseEntity.ok(username2JSON.toString());
    }

    @Override
    public ResponseEntity<String> getLoggedUserData(ObjectId id, Principal principal) {
        if(!checker.checkSameUser(id, principal)){
            return ResponseEntity.status(403).build();
        }
        UserDB userDB = userDBService.returnUserById(id);
        RetrieveUserDTO retrieveUserDTO = new RetrieveUserDTO(userDB.getUsername(),
                userDB.getFirstName(), userDB.getLastName(), userDB.getMobile(), userDB.getEmail());
        return ResponseEntity.ok(retrieveUserDTO.toJSON());
    }

    @Override
    public ResponseEntity<Void> deleteUser(ObjectId id, Principal principal) {
        checker.checkSameUser(id, principal);
        userDBService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateUserData(ObjectId id, UpdateUserDataDTO dto) {
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

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updatePassword(ObjectId id, UpdatePasswordDTO dto) {
        UserDB moddedUser = userDBService.returnUserById(id);
        UserDB modificationUser = userDBService.returnLoggedUser();

        moddedUser.setPassword(passwordEncoder.encode(dto.getPassword()));

        moddedUser.setModificationPerson(modificationUser);
        moddedUser.setModificationDate(LocalDateTime.now());

        assetsRepository.save(moddedUser.getAssets());
        userDBRepository.save(moddedUser);
        return ResponseEntity.ok().build();
    }
}
