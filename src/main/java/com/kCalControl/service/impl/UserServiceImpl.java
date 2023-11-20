package com.kCalControl.service.impl;

import com.kCalControl.config.Checker;
import com.kCalControl.dto.SearchParamsDTO;
import com.kCalControl.dto.credentials.UpdateCredentialsDTO;
import com.kCalControl.dto.user.NewUserDTO;
import com.kCalControl.dto.user.UpdateUserDataDTO;
import com.kCalControl.exceptions.NetworkException;
import com.kCalControl.model.*;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.repository.RoleRepository;
import com.kCalControl.repository.UserRepository;
import com.kCalControl.service.UserService;
import com.kCalControl.service.WhoAmI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BMDataRepository bmDataRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    Checker checker;
    @Autowired
    WhoAmI whoAmI;

    @Override
    public User newUser(NewUserDTO dto) {

        User user = new User(dto.getName(), dto.getMobile());
        BMData bmData = new BMData();
        user.setBmData(bmData);
        Credentials credentials = newCredentials(dto);
        user.setCredentials(credentials);

        user.setAssets(new Assets(user, Date.from(Instant.now()), user, Date.from(Instant.now())));
        bmData.setAssets(new Assets(user, Date.from(Instant.now()), user, Date.from(Instant.now())));
        credentials.setAssets(new Assets(user, Date.from(Instant.now()), user, Date.from(Instant.now())));

        Set<Role> roles = new HashSet<>();
        String rolesString = dto.getRole();
        if (rolesString != null && !rolesString.isEmpty()) {
            String[] rolesArray = rolesString.split(",");
            for (String role : rolesArray) {
                role = role.trim();
                if (role.equalsIgnoreCase("ADMIN")) {
                    if (whoAmI.whoAmI() == null) {
                        continue;
                    } else if (!checker.checkRoleAdmin()) {
                        continue;
                    }
                }
                String finalRole = role;
                Role roleEntity = roleRepository.findByRoleName(role)
                        .orElseThrow(() -> new NetworkException("Role with name: " + finalRole + " not found", HttpStatus.NOT_FOUND));
                roles.add(roleEntity);
            }
            user.setRoles(roles);
        }
        return user;
    }

    @Override
    public Credentials newCredentials(NewUserDTO dto) {
        Credentials credentials = new Credentials();
        credentials.setUsername(dto.getUsername());
        credentials.setEmail(dto.getEmail());
        credentials.setPassword(passwordEncoder.encode(dto.getPassword()));
        credentials.setPasswordDate(Date.from(Instant.now()));
        return credentials;
    }


    @Override
    public User returnUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NetworkException("User with id: " + id + " not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<User> getUsers(int page, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "username");
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);
        return userRepository.findAll(pageRequest);
    }

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
        return userRepository.findByNameLikeIgnoreCaseOrMobileLike(dto.getQuery(), dto.getQuery(), dto.getQuery(), pageRequest);
    }

    @Override
    public User updateUserData(Integer id, UpdateUserDataDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new NetworkException("User with id: " + id + " not found", HttpStatus.NOT_FOUND));

        user.setName(dto.getName());
        user.setMobile(dto.getMobile());
        user.setModificationPerson(whoAmI.currentUser().orElseThrow(() -> new NetworkException("User with id: " + id + " not found", HttpStatus.NOT_FOUND)));
        user.setModificationDate(Date.from(Instant.now()));

        return user;
    }

    @Override
    public User updateCredentials(Integer id, UpdateCredentialsDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new NetworkException("User to update with id: " + id + " not found", HttpStatus.NOT_FOUND));
        Integer modificationPerson = whoAmI.whoAmI();
        Credentials credentials = user.getCredentials();

        credentials.setPassword(passwordEncoder.encode(dto.getPassword()));
        credentials.setPasswordDate((java.sql.Date) Time.from(Instant.now()));
        user.setModificationPerson(whoAmI.currentUser().orElseThrow(() -> new NetworkException("User with id: " + id + " not found", HttpStatus.NOT_FOUND)));
        user.setModificationDate(Date.from(Instant.now()));
        return user;
    }

    @Override
    public void deleteUser(Integer id) {
        if(checker.checkUserExistsById(id)){
            userRepository.deleteById(id);
        }else{
            throw new NetworkException("User with id: " + id + " not found; cannot delete.", HttpStatus.NOT_FOUND);
        }
    }

}
