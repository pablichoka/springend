package com.kCalControl.service.impl;

import com.kCalControl.dto.creation.NewUserDTO;
import com.kCalControl.dto.update.UpdatePasswordDTO;
import com.kCalControl.dto.update.UpdateUserDataDTO;
import com.kCalControl.model.Assets;
import com.kCalControl.model.BMData;
import com.kCalControl.model.Role;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.AssetsRepository;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.repository.RoleRepository;
import com.kCalControl.repository.UserDBRepository;
import com.kCalControl.service.UserDBService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserDBServiceImpl implements UserDBService {

    private static final Logger logger = Logger.getLogger(UserDBServiceImpl.class.getName());
    @Autowired
    UserDBRepository userDBRepository;
    @Autowired
    AssetsRepository assetsRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BMDataRepository bmDataRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDB newAdminUser(ObjectId creationPersonId, NewUserDTO dto, String role){

        UserDB creationPerson;

        UserDB userDB = new UserDB();
        userDB.setId(new ObjectId());
        userDB.setUsername(dto.getUsername());
        userDB.setFirstName(dto.getFirstName());
        userDB.setLastName(dto.getLastName());
        userDB.setEmail(dto.getEmail());
        userDB.setMobile(dto.getMobile());
        userDB.setPassword(passwordEncoder.encode(dto.getPassword()));

        LocalDateTime time = LocalDateTime.now();
        Assets assets = new Assets();

        if(userDBRepository.findById(creationPersonId).isPresent()){
            creationPerson = userDBRepository.findById(creationPersonId).get();
        }else{
            creationPerson = null;
        }

        userDB.setPasswordDate(time);
        assets.setCreationPerson(creationPerson);
        assets.setCreationDate(time);
        assets.setModificationPerson(creationPerson);
        assets.setModificationDate(time);
        userDB.setAssets(assets);
        userDB.setRole(roleRepository.findByRoleName(role).get());

        BMData bmData = new BMData();
        bmData.setUserAssoc(userDB);

        userDB.setBmData(bmData);

        return userDB;
    }

    @Override
    public UserDB newNormalUser(NewUserDTO dto){

        UserDB userDB = new UserDB();
        userDB.setId(new ObjectId());
        userDB.setUsername(dto.getUsername());
        userDB.setFirstName(dto.getFirstName());
        userDB.setLastName(dto.getLastName());
        userDB.setEmail(dto.getEmail());
        userDB.setMobile(dto.getMobile());
        userDB.setPassword(passwordEncoder.encode(dto.getPassword()));

        LocalDateTime time = LocalDateTime.now();
        Assets assets = new Assets();

        userDB.setPasswordDate(time);
        assets.setCreationPerson(userDB);
        assets.setCreationDate(time);
        assets.setModificationPerson(userDB);
        assets.setModificationDate(time);
        userDB.setAssets(assets);
        userDB.setRole(roleRepository.findByRoleName("USER").get());

        BMData bmData = new BMData();
        bmData.setUserAssoc(userDB);

        userDB.setBmData(bmData);

        return userDB;
    }

    @Override
    public UserDB returnUserById(ObjectId id){
        return userDBRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("The user does not exist"));
    }

    @Override
    public UserDB returnLoggedUser(){
        return userDBRepository.findByUsername(getUsernameLoggedUser())
                .orElseThrow(() -> new UsernameNotFoundException("The user does not exist"));
    }

    @Override
    public String getUsernameLoggedUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        Optional<UserDB> user;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        return username;
        } else {
            user = userDBRepository.findById(new ObjectId(principal.toString()));
            if(user.isPresent()){
                username = user.get().getUsername();
            }else throw new UsernameNotFoundException("Username not found");
        }
        return username;
    }

    @Override
    public Page<UserDB> getUsers(int page, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "username");
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);
        return userDBRepository.findAll(pageRequest);
    }

    //TODO find a solution to implement sorting by date
    @Override
    public Page<UserDB> getUsersFromSearch(int page, int pageSize, String query, String filter, String sort) {
        Sort sorted = null;
        if (filter.isEmpty()){filter = "username";}//In case not filtering, target will be username

        sorted = switch (sort) {
            case "az" -> Sort.by(Sort.Direction.ASC, filter);
            case "za" -> Sort.by(Sort.Direction.DESC, filter);
//            case "newer": sorted = Sort.by(Sort.Direction.DESC, "getCreationDate()"); break;
//            case "older": sorted = Sort.by(Sort.Direction.ASC, "getCreationDate()"); break;
//            case "newerM": sorted = Sort.by(Sort.Direction.DESC, "getModificationDate()"); break;
//            case "olderM": sorted = Sort.by(Sort.Direction.ASC, "getModificationDate()"); break;
            default -> Sort.unsorted();
        };
        PageRequest pageRequest = PageRequest.of(page, pageSize, sorted);
        switch (filter) {
            case "username" -> {
                return userDBRepository.findByUsernameLike(query, pageRequest);
            }
            case "email" -> {
                return userDBRepository.findByEmailLike(query, pageRequest);
            }
            case "role" -> {
                Optional<Role> role = roleRepository.findByRoleNameLike(query);
                if (role.isPresent()) {
                    Role roleQ = role.get();
                    return userDBRepository.findByRole_Id(roleQ.getId(), pageRequest);
                }
            }
            case "firstName" -> {
                return userDBRepository.findByFirstNameLike(query, pageRequest);
            }
            case "lastName" -> {
                return userDBRepository.findByLastNameLike(query, pageRequest);
            }
            default -> {
                return userDBRepository.findAll(pageRequest);
            }
        }
        return null;
    }

    @Override
    public UserDB updateUserData(ObjectId id, UpdateUserDataDTO dto, Principal principal){
        UserDB userDB = userDBRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("The user that you want to update does not exist"));
        UserDB modificationPerson = userDBRepository.findByUsername(principal.getName()).get();

        userDB.setFirstName(dto.getFirstName());
        userDB.setLastName(dto.getLastName());
        userDB.setMobile(dto.getMobile());
        userDB.setEmail(dto.getEmail());
        userDB.setModificationPerson(modificationPerson);
        userDB.setModificationDate(LocalDateTime.now());

        return userDB;
    }

    @Override
    public UserDB updatePassword(ObjectId id, UpdatePasswordDTO dto, Principal principal){
        UserDB userDB = userDBRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("The user that you want to update does not exist"));
        UserDB modificationPerson = userDBRepository.findByUsername(principal.getName()).get();

        LocalDateTime time = LocalDateTime.now();

        userDB.setPassword(passwordEncoder.encode(dto.getPassword()));
        userDB.setPasswordDate(time);
        userDB.setModificationPerson(modificationPerson);
        userDB.setModificationDate(time);

        return userDB;
    }

    @Override
    public void deleteUserFromAdmin(ObjectId id){
        UserDB userDB = userDBRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("The user that you want to delete does not exist"));
        assetsRepository.delete(userDB.getAssets());
        bmDataRepository.delete(userDB.getBmData());
        userDBRepository.deleteById(id);
    }

}
