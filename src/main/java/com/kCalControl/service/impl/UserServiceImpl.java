package com.kCalControl.service.impl;

import com.kCalControl.dto.SearchParamsDTO;
import com.kCalControl.dto.credentials.NewCredentialsDTO;
import com.kCalControl.dto.user.NewUserDTO;
import com.kCalControl.dto.user.UpdatePasswordDTO;
import com.kCalControl.dto.user.UpdateUserDataDTO;
import com.kCalControl.exceptions.NetworkException;
import com.kCalControl.model.BMData;
import com.kCalControl.model.Credentials;
import com.kCalControl.model.Role;
import com.kCalControl.model.User;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.repository.RoleRepository;
import com.kCalControl.repository.UserDBRepository;
import com.kCalControl.service.UserDBService;
import com.kCalControl.service.WhoAmI;
import jakarta.transaction.Transactional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Stream;

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
    WhoAmI whoAmI;

    @Override
    public User newUser(NewUserDTO dto) {

        User user = new User(dto.getName(), dto.getMobile());
        user.setCreationPerson(whoAmI.whoIAm());
        user.setCreationDate(Date.from(Instant.now()));
        user.setModificationPerson(whoAmI.whoIAm());
        user.setModificationDate(Date.from(Instant.now()));
        BMData bmData = new BMData();
        bmData.setUserAssoc(user);

        user.setBmData(bmData);

        Set<Role> roles = new HashSet<>();
        String rolesString = dto.getRole();
        if (rolesString != null && !rolesString.isEmpty()) {
            String[] rolesArray = rolesString.split(",");
            Stream.of(rolesArray)
                    .map(String::trim)
                    .forEach(role -> {
                        Role roleEntity = roleRepository.findById(role)
                                .orElseThrow(() -> new NetworkException("Role " + role + " not found", HttpStatus.NOT_FOUND));
                        roles.add(roleEntity);
                    });
        }
        user.setRoles(roles);

        return user;
    }

    @Override
    public Credentials newCredentials(NewCredentialsDTO dto) {
        Credentials credentials = new Credentials();
        credentials.setUsername(dto.getUsername());
        credentials.setEmail(dto.getEmail());
        credentials.setPassword(passwordEncoder.encode(dto.getPassword()));
        credentials.setPasswordDate(Date.from(Instant.now()));
        return credentials;
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
        user.setModificationPerson(whoAmI.whoIAm());
        user.setModificationDate(Date.from(Instant.now()));

        return user;
    }

    @Transactional
    @Override
    public User updateCredentials(Integer id, UpdatePasswordDTO dto) {
        User user = userDBRepository.findById(id)
                .orElseThrow(() -> new NetworkException("User to update with id: " + id + " not found", HttpStatus.NOT_FOUND));
        Integer modificationPerson = whoAmI.whoIAm();
        Credentials credentials = user.getCredentials();

        credentials.setPassword(passwordEncoder.encode(dto.getPassword()));
        credentials.setPasswordDate(Date.from(Instant.now()));
        user.setModificationPerson(modificationPerson);
        user.setModificationDate(Date.from(Instant.now()));
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
