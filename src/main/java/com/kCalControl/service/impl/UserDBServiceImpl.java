package com.kCalControl.service.impl;

import com.kCalControl.dto.SearchParamsDTO;
import com.kCalControl.dto.user.NewUserDTO;
import com.kCalControl.dto.user.UpdatePasswordDTO;
import com.kCalControl.dto.user.UpdateUserDataDTO;
import com.kCalControl.exceptions.NetworkException;
import com.kCalControl.model.BMData;
import com.kCalControl.model.Role;
import com.kCalControl.model.User;
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
    public User newAdminUser(ObjectId id, NewUserDTO dto) {

        User creationPerson;

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setMobile(dto.getMobile());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        LocalDateTime time = LocalDateTime.now();

        user.setPasswordDate(time);
        user.setCreationPerson(whoIAm.currentUser().orElseThrow(() -> new NetworkException("God user not found, did you log in?", HttpStatus.NOT_FOUND)));
        user.setCreationDate(time);
        user.setModificationPerson(whoIAm.currentUser().orElseThrow(() -> new NetworkException("God user not found, did you log in?", HttpStatus.NOT_FOUND)));
        user.setModificationDate(time);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(dto.getRole()).orElseThrow(()-> new NetworkException("Role not found", HttpStatus.NOT_FOUND)));
        user.setRoles(roles);

        BMData bmData = new BMData();
        bmData.setUserAssoc(user);

        user.setBmData(bmData);

        return user;
    }

    @Override
    public User newNormalUser(NewUserDTO dto) {

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setMobile(dto.getMobile());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        LocalDateTime time = LocalDateTime.now();

        user.setPasswordDate(time);
        user.setCreationPerson(user);
        user.setCreationDate(time);
        user.setModificationPerson(user);
        user.setModificationDate(time);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById("USER").orElseThrow(() -> new NetworkException("Role not found", HttpStatus.NOT_FOUND)));
        user.setRoles(roles);

        BMData bmData = new BMData();
        bmData.setUserAssoc(user);

        user.setBmData(bmData);

        return user;
    }

    @Override
    public User returnUserById(Integer id) {
        return userDBRepository.findById(id)
                .orElseThrow(() -> new NetworkException("User with id: " + id + " not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<User> getUsers(int page, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "username");
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);
        return userDBRepository.findAll(pageRequest);
    }

    //TODO find a solution to implement sorting by date
    @Override
    public Page<User> getUsersFromSearch(SearchParamsDTO dto) {
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
    public User updateUserData(Integer id, UpdateUserDataDTO dto) {
        User user = userDBRepository.findById(id)
                .orElseThrow(() -> new NetworkException("User with id: " + id + " not found", HttpStatus.NOT_FOUND));
        User modificationPerson = userDBRepository.findById(dto.getUpdaterId())
                .orElseThrow(() -> new NetworkException("Updater user with id: " + id + " not found", HttpStatus.NOT_FOUND));

        user.setName(dto.getName());
        user.setMobile(dto.getMobile());
        user.setEmail(dto.getEmail());
        user.setModificationPerson(whoIAm.currentUser().orElseThrow());
        user.setModificationDate(LocalDateTime.now());

        return user;
    }

    @Override
    public User updatePassword(Integer id, UpdatePasswordDTO dto) {
        User user = userDBRepository.findById(id)
                .orElseThrow(() -> new NetworkException("User to update with id: " + id + " not found", HttpStatus.NOT_FOUND));
        User modificationPerson = userDBRepository.findById(dto.getUpdaterId())
                .orElseThrow(() -> new NetworkException("Updater user with id: " + id + " not found", HttpStatus.NOT_FOUND));

        LocalDateTime time = LocalDateTime.now();

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setPasswordDate(time);
        user.setModificationPerson(whoIAm.currentUser().orElseThrow(() -> new NetworkException("Missing modification person id, did you log in?", HttpStatus.NOT_FOUND)));
        user.setModificationDate(time);
        return user;
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userDBRepository.findById(id)
                .orElseThrow(() -> new NetworkException("User to delete with id: " + id + " not found", HttpStatus.NOT_FOUND));
        bmDataRepository.delete(user.getBmData());
        userDBRepository.deleteById(id);
    }

}
