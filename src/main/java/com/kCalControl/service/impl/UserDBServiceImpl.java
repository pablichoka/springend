package com.kCalControl.service.impl;

import com.kCalControl.dto.SearchParamsDTO;
import com.kCalControl.dto.user.NewUserDTO;
import com.kCalControl.dto.user.UpdatePasswordDTO;
import com.kCalControl.dto.user.UpdateUserDataDTO;
import com.kCalControl.exceptions.NetworkException;
import com.kCalControl.model.BMData;
import com.kCalControl.model.Role;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.repository.RoleRepository;
import com.kCalControl.repository.UserDBRepository;
import com.kCalControl.service.UserDBService;
import com.kCalControl.service.WhoIAm;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class UserDBServiceImpl implements UserDBService {

    private static final Logger logger = Logger.getLogger(UserDBServiceImpl.class.getName());
    @Autowired
    UserDBRepository userDBRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BMDataRepository bmDataRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    WhoIAm whoIAm;

    @Override
    public UserDB newAdminUser(ObjectId id, NewUserDTO dto) {

        UserDB creationPerson;

        UserDB userDB = new UserDB();
        userDB.setUsername(dto.getUsername());
        userDB.setName(dto.getName());
        userDB.setEmail(dto.getEmail());
        userDB.setMobile(dto.getMobile());
        userDB.setPassword(passwordEncoder.encode(dto.getPassword()));

        LocalDateTime time = LocalDateTime.now();

        userDB.setPasswordDate(time);
        userDB.setCreationPerson(whoIAm.currentUser().orElseThrow(() -> new NetworkException("God user not found, did you log in?", HttpStatus.NOT_FOUND)));
        userDB.setCreationDate(time);
        userDB.setModificationPerson(whoIAm.currentUser().orElseThrow(() -> new NetworkException("God user not found, did you log in?", HttpStatus.NOT_FOUND)));
        userDB.setModificationDate(time);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(dto.getRole()).orElseThrow(()-> new NetworkException("Role not found", HttpStatus.NOT_FOUND)));
        userDB.setRoles(roles);

        BMData bmData = new BMData();
        bmData.setUserAssoc(userDB);

        userDB.setBmData(bmData);

        return userDB;
    }

    @Override
    public UserDB newNormalUser(NewUserDTO dto) {

        UserDB userDB = new UserDB();
        userDB.setUsername(dto.getUsername());
        userDB.setName(dto.getName());
        userDB.setEmail(dto.getEmail());
        userDB.setMobile(dto.getMobile());
        userDB.setPassword(passwordEncoder.encode(dto.getPassword()));

        LocalDateTime time = LocalDateTime.now();

        userDB.setPasswordDate(time);
        userDB.setCreationPerson(userDB);
        userDB.setCreationDate(time);
        userDB.setModificationPerson(userDB);
        userDB.setModificationDate(time);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById("USER").orElseThrow(() -> new NetworkException("Role not found", HttpStatus.NOT_FOUND)));
        userDB.setRoles(roles);

        BMData bmData = new BMData();
        bmData.setUserAssoc(userDB);

        userDB.setBmData(bmData);

        return userDB;
    }

    @Override
    public UserDB returnUserById(Integer id) {
        return userDBRepository.findById(id)
                .orElseThrow(() -> new NetworkException("User with id: " + id + " not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<UserDB> getUsers(int page, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "username");
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);
        return userDBRepository.findAll(pageRequest);
    }

    //TODO find a solution to implement sorting by date
    @Override
    public Page<UserDB> getUsersFromSearch(SearchParamsDTO dto) {
        Sort sorted = null;
        sorted = switch (dto.getSort()) {
            case "az" -> Sort.by(Sort.Direction.ASC, "username", "email", "firstName", "lastName");
            case "za" -> Sort.by(Sort.Direction.DESC, "username", "email", "firstName", "lastName");
            case "newer" -> Sort.by(Sort.Direction.DESC, "creationDate");
            case "older" -> Sort.by(Sort.Direction.ASC, "creationDate");
            case "newerM" -> Sort.by(Sort.Direction.DESC, "modificationDate");
            case "olderM" -> Sort.by(Sort.Direction.ASC, "modificationDate");
            default -> Sort.unsorted();
        };
        PageRequest pageRequest = PageRequest.of(dto.getPage(), dto.getPageSize(), sorted);
        return userDBRepository.findByUsernameLikeIgnoreCaseOrEmailIgnoreCaseOrNameLikeIgnoreCase(dto.getQuery(), dto.getQuery(), dto.getQuery(), pageRequest);
    }

    @Override
    public UserDB updateUserData(Integer id, UpdateUserDataDTO dto) {
        UserDB userDB = userDBRepository.findById(id)
                .orElseThrow(() -> new NetworkException("User with id: " + id + " not found", HttpStatus.NOT_FOUND));
        UserDB modificationPerson = userDBRepository.findById(dto.getUpdaterId())
                .orElseThrow(() -> new NetworkException("Updater user with id: " + id + " not found", HttpStatus.NOT_FOUND));

        userDB.setName(dto.getName());
        userDB.setMobile(dto.getMobile());
        userDB.setEmail(dto.getEmail());
        userDB.setModificationPerson(whoIAm.currentUser().orElseThrow());
        userDB.setModificationDate(LocalDateTime.now());

        return userDB;
    }

    @Override
    public UserDB updatePassword(Integer id, UpdatePasswordDTO dto) {
        UserDB userDB = userDBRepository.findById(id)
                .orElseThrow(() -> new NetworkException("User to update with id: " + id + " not found", HttpStatus.NOT_FOUND));
        UserDB modificationPerson = userDBRepository.findById(dto.getUpdaterId())
                .orElseThrow(() -> new NetworkException("Updater user with id: " + id + " not found", HttpStatus.NOT_FOUND));

        LocalDateTime time = LocalDateTime.now();

        userDB.setPassword(passwordEncoder.encode(dto.getPassword()));
        userDB.setPasswordDate(time);
        userDB.setModificationPerson(whoIAm.currentUser().orElseThrow(() -> new NetworkException("Missing modification person id, did you log in?", HttpStatus.NOT_FOUND)));
        userDB.setModificationDate(time);
        return userDB;
    }

    @Override
    public void deleteUser(Integer id) {
        UserDB userDB = userDBRepository.findById(id)
                .orElseThrow(() -> new NetworkException("User to delete with id: " + id + " not found", HttpStatus.NOT_FOUND));
        bmDataRepository.delete(userDB.getBmData());
        userDBRepository.deleteById(id);
    }

}
