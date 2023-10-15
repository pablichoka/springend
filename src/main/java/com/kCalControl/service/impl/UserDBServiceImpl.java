package com.kCalControl.service.impl;

import com.kCalControl.dto.SearchParamsDTO;
import com.kCalControl.dto.user.NewUserDTO;
import com.kCalControl.dto.user.UpdatePasswordDTO;
import com.kCalControl.dto.user.UpdateUserDataDTO;
import com.kCalControl.model.Assets;
import com.kCalControl.model.BMData;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public UserDB newAdminUser(ObjectId id, NewUserDTO dto){

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

        if(userDBRepository.findById(returnUserById(id).getId()).isPresent()){
            creationPerson = userDBRepository.findById(returnUserById(id).getId()).get();
        }else{
            creationPerson = null;
        }

        userDB.setPasswordDate(time);
        assets.setCreationPerson(creationPerson);
        assets.setCreationDate(time);
        assets.setModificationPerson(creationPerson);
        assets.setModificationDate(time);
        userDB.setAssets(assets);
        userDB.setRole(roleRepository.findByRoleName(dto.getRole()).get());

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

//    @Override
//    public UserDB returnLoggedUser(ObjectId id){
//        return userDBRepository.findByUsername(returnUserById(id))
//                .orElseThrow(() -> new UsernameNotFoundException("The user does not exist"));
//    }

//    @Override
//    public String getUsernameLoggedUser(){
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username;
//        Optional<UserDB> user;
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails)principal).getUsername();
//        return username;
//        } else {
//            user = userDBRepository.findById(new ObjectId(principal.toString()));
//            if(user.isPresent()){
//                username = user.get().getUsername();
//            }else throw new UsernameNotFoundException("Username not found");
//        }
//        return username;
//    }

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
            case "newer" -> Sort.by(Sort.Direction.DESC, "userDB.assets.creationDate");
            case "older" -> Sort.by(Sort.Direction.ASC, "userDB.assets.creationDate");
            case "newerM" -> Sort.by(Sort.Direction.DESC, "userDB.assets.modificationDate");
            case "olderM"-> Sort.by(Sort.Direction.ASC, "userDB.assets.modificationDate");
            default -> Sort.unsorted();
        };
        PageRequest pageRequest = PageRequest.of(dto.getPage(), dto.getPageSize(), sorted);
        return userDBRepository.findByUsernameLikeIgnoreCaseOrEmailIgnoreCaseOrFirstNameLikeIgnoreCase(dto.getQuery(), dto.getQuery(), dto.getQuery(), pageRequest);
    }

    @Override
    public UserDB updateUserData(ObjectId id, UpdateUserDataDTO dto){
        UserDB userDB = userDBRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("The user that you want to update does not exist"));
        UserDB modificationPerson = userDBRepository.findById(dto.getUpdaterId())
                .orElseThrow(() -> new UsernameNotFoundException("The updater user does not exist"));

        userDB.setFirstName(dto.getFirstName());
        userDB.setLastName(dto.getLastName());
        userDB.setMobile(dto.getMobile());
        userDB.setEmail(dto.getEmail());
        userDB.setModificationPerson(modificationPerson);
        userDB.setModificationDate(LocalDateTime.now());

        return userDB;
    }

    @Override
    public UserDB updatePassword(ObjectId id, UpdatePasswordDTO dto){
        UserDB userDB = userDBRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("The user that you want to update does not exist"));
        UserDB modificationPerson = userDBRepository.findById(dto.getUpdaterId())
                .orElseThrow(() -> new UsernameNotFoundException("The updater user does not exist"));

        LocalDateTime time = LocalDateTime.now();

        userDB.setPassword(passwordEncoder.encode(dto.getPassword()));
        userDB.setPasswordDate(time);
        userDB.setModificationPerson(modificationPerson);
        userDB.setModificationDate(time);
        return userDB;
    }

    @Override
    public void deleteUser(ObjectId id){
        UserDB userDB = userDBRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("The user that you want to delete does not exist"));
        assetsRepository.delete(userDB.getAssets());
        bmDataRepository.delete(userDB.getBmData());
        userDBRepository.deleteById(id);
    }

}
