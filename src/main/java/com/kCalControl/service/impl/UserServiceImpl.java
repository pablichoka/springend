package com.kCalControl.service.impl;

import com.kCalControl.config.Checker;
import com.kCalControl.dto.credentials.UpdateCredentialsDTO;
import com.kCalControl.dto.user.NewUserDTO;
import com.kCalControl.dto.user.UpdateUserDataDTO;
import com.kCalControl.exceptions.NetworkException;
import com.kCalControl.model.*;
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

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Checker checker;
    private final WhoAmI whoAmI;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, Checker checker, WhoAmI whoAmI) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.checker = checker;
        this.whoAmI = whoAmI;
    }

    @Override
    public User newUser(NewUserDTO dto) {

        User user = new User(dto.getName(), dto.getMobile());
        BMData bmData = new BMData();
        user.setBmData(bmData);
        Credentials credentials = newCredentials(dto);
        user.setCredentials(credentials);

        Set<Role> roles = new HashSet<>();
        String rolesString = dto.getRole();
        if (rolesString != null && !rolesString.isEmpty()) {
            String[] rolesArray = rolesString.split(",");
            for (String role : rolesArray) {
                role = role.trim();
                if (role.equalsIgnoreCase("ADMIN")) {
                    if (whoAmI.currentUser().isEmpty()) {
                        continue;
                    } else if (!checker.checkRoleAdmin()) {
                        continue;
                    }
                }
                String finalRole = role;
                Role roleEntity = roleRepository.findByRoleName(role).orElseThrow(() -> new NetworkException("Role with name: " + finalRole + " not found", HttpStatus.NOT_FOUND));
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
    public Page<User> getUsers(int page, int pageSize, String sort, String query, String searchBy) {
        PageRequest pageRequest;
        String searchedBy = switch (searchBy) {
            case "mobile" -> "mobile";
            case "email" -> "credentials.email";
            case "username" -> "credentials.username";
            case null, default -> "name";
        };
        if (query == null || query.isEmpty()) {
            pageRequest = searchUsers(page, pageSize, sort, searchedBy);
            return userRepository.findAll(pageRequest);
        } else {
            pageRequest = searchUsers(page, pageSize, sort, searchedBy);
            switch (searchBy) {
                case "mobile" -> {
                    return userRepository.findByMobileLike(query, pageRequest);
                }
                case "email" -> {
                    return userRepository.findByCredentials_EmailLikeIgnoreCase(query, pageRequest);
                }
                case "username" -> {
                    return userRepository.findByCredentials_UsernameLikeIgnoreCase(query, pageRequest);
                }
                case null, default -> {
                    return userRepository.findByNameLikeIgnoreCase(query, pageRequest);
                }
            }
        }
    }

    private PageRequest searchUsers(int page, int pageSize, String sort, String searchedBy) {
        Sort sorted = switch (sort) {
            case "az" -> Sort.by(Sort.Direction.ASC, searchedBy);
            case "za" -> Sort.by(Sort.Direction.DESC, searchedBy);
            case "newer" -> Sort.by(Sort.Direction.DESC, "assets.creationDate");
            case "older" -> Sort.by(Sort.Direction.ASC, "assets.creationDate");
            case "newerM" -> Sort.by(Sort.Direction.DESC, "assets.modificationDate");
            case "olderM" -> Sort.by(Sort.Direction.ASC, "assets.modificationDate");
            case null, default -> Sort.unsorted();
        };
        return PageRequest.of(page, pageSize, sorted);
    }

    @Override
    public User updateUserData(Integer id, UpdateUserDataDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new NetworkException("User with id: " + id + " not found", HttpStatus.NOT_FOUND));

        user.setName(dto.getName());
        user.setMobile(dto.getMobile());
        user.setModificationPerson(whoAmI.whoAmI().orElseThrow(() -> new NetworkException("User not logged", HttpStatus.FORBIDDEN)));
        user.setModificationDate(Date.from(Instant.now()));

        return user;
    }

    @Override
    public User updateCredentials(Integer id, UpdateCredentialsDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new NetworkException("User to update with id: " + id + " not found", HttpStatus.NOT_FOUND));
        Integer modificationPerson = whoAmI.whoAmI().orElseThrow(() -> new NetworkException("User not logged", HttpStatus.FORBIDDEN));

        Credentials credentials = user.getCredentials();
        credentials.setPassword(passwordEncoder.encode(dto.getPassword()));
        credentials.setPasswordDate(Date.from(Instant.now()));
        user.setModificationPerson(whoAmI.whoAmI().orElseThrow(() -> new NetworkException("User not logged", HttpStatus.FORBIDDEN)));
        user.setModificationDate(Date.from(Instant.now()));
        return user;
    }

    @Override
    public void deleteUser(Integer id) {
        if (checker.checkUserExistsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new NetworkException("User with id: " + id + " not found; cannot delete.", HttpStatus.NOT_FOUND);
        }
    }

}
