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
    public ResponseEntity<String> createNormalUser(NewUserDTO dto) {
        UserDB newUserDB = userDBService.newNormalUser(dto);
        bmDataRepository.save(newUserDB.getBmData());
        assetsRepository.save(newUserDB.getAssets());
        userDBRepository.save(newUserDB);

        return ResponseEntity.ok("User created successfully");
    }

    @Override
    public ResponseEntity<String> getLoggedUserData(ObjectId id) {
        if(checker.checkSameUser(id)){
            return ResponseEntity.status(403).build();
        }
        UserDB userDB = userDBService.returnUserById(id);
        RetrieveUserDTO retrieveUserDTO = new RetrieveUserDTO(userDB.getUsername(),
                userDB.getFirstName(), userDB.getLastName(), userDB.getMobile(), userDB.getEmail());
        return ResponseEntity.ok(retrieveUserDTO.toJSON());
    }

    @Override
    public ResponseEntity<String> deleteUser(ObjectId id) {
        if(checker.checkSameUser(id)){
            return ResponseEntity.status(403).build();
        }
        userDBService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @Override
    public ResponseEntity<String> updateUserData(ObjectId id, UpdateUserDataDTO dto) {
        if(checker.checkSameUser(id, dto.getUpdaterId())){
            return ResponseEntity.status(403).build();
        }
        UserDB updatedUser = userDBService.updateUserData(id, dto);
        assetsRepository.save(updatedUser.getAssets());
        userDBRepository.save(updatedUser);

        return ResponseEntity.ok("User data updated successfully");
    }

    @Override
    public ResponseEntity<String> updatePassword(ObjectId id, UpdatePasswordDTO dto) {
        if(checker.checkSameUser(id)){
            return ResponseEntity.status(403).build();
        }
        UserDB updatedUser = userDBService.updatePassword(id, dto);
        assetsRepository.save(updatedUser.getAssets());
        userDBRepository.save(updatedUser);
        return ResponseEntity.ok("Password updated successfully");
    }
}
