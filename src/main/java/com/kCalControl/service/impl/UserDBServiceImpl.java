package com.kCalControl.service.impl;

import com.kCalControl.dto.NewUserDTO;
import com.kCalControl.dto.UpdatePasswordDTO;
import com.kCalControl.dto.UpdatePersonalDataDTO;
import com.kCalControl.dto.UpdateUserDataDTO;
import com.kCalControl.model.Assets;
import com.kCalControl.model.Role;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.AssetsRepository;
import com.kCalControl.repository.RoleRepository;
import com.kCalControl.repository.UserRepository;
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
    UserRepository userRepository;
    @Autowired
    AssetsRepository assetsRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDB newUser(ObjectId creationPersonId, NewUserDTO dto, String role){
        UserDB creationPerson = userRepository.findById(creationPersonId)
                .orElseThrow(() -> new UsernameNotFoundException("The creator does not exist"));

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
        assets.setCreationPerson(creationPerson);
        assets.setCreationDate(time);
        assets.setModificationPerson(creationPerson);
        assets.setModificationDate(time);
        userDB.setAssets(assets);
        userDB.setRole(roleRepository.findByRoleName(role).get());

        return userDB;
    }

    @Override
    public UserDB returnUserById(ObjectId id){
        UserDB userDB = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("The user does not exist"));
        return userDB;
    }

    @Override
    public UserDB returnLoggedUser(){
        UserDB userDB = userRepository.findByUsername(getUsernameLoggedUser())
                .orElseThrow(() -> new UsernameNotFoundException("The user does not exist"));
        return userDB;
    }

    @Override
    public String getUsernameLoggedUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    @Override
    public Page<UserDB> getUsers(int page, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "username");
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);
        return userRepository.findAll(pageRequest);
    }

    //TODO find a solution to implement sorting by date
    @Override
    public Page<UserDB> getUsersFromSearch(int page, int pageSize, String query, String filter, String sort) {
        Sort sorted = null;
        if (filter.equals("")){filter = "username";}//In case not filtering, target will be username

        switch(sort){
            case "az": sorted = Sort.by(Sort.Direction.ASC, filter); break;
            case "za": sorted = Sort.by(Sort.Direction.DESC, filter); break;
//            case "newer": sorted = Sort.by(Sort.Direction.DESC, "getCreationDate()"); break;
//            case "older": sorted = Sort.by(Sort.Direction.ASC, "getCreationDate()"); break;
//            case "newerM": sorted = Sort.by(Sort.Direction.DESC, "getModificationDate()"); break;
//            case "olderM": sorted = Sort.by(Sort.Direction.ASC, "getModificationDate()"); break;
            default: sorted = Sort.unsorted(); break;
        }
        PageRequest pageRequest = PageRequest.of(page, pageSize, sorted);
        if(filter.equals("username")){
            return userRepository.findByUsernameLike(query, pageRequest);
        } else if (filter.equals("email")) {
            return userRepository.findByEmailLike(query, pageRequest);
        } else if (filter.equals("role")) {
            Optional<Role> role = roleRepository.findByRoleNameLike(query);
            if(role.isPresent()) {
                Role roleQ = role.get();
                return userRepository.findByRole_Id(roleQ.getId(), pageRequest);
            }
        } else if (filter.equals("firstName")) {
            return userRepository.findByFirstNameLike(query, pageRequest);
        } else if (filter.equals("lastName")) {
            return userRepository.findByLastNameLike(query, pageRequest);
        } else {
            return userRepository.findAll(pageRequest);
        }
        throw new UsernameNotFoundException("User cannot be found");
    }

    @Override
    public UserDB updatePersonalData(ObjectId id, UpdatePersonalDataDTO dto, Principal principal){

        UserDB userDB = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("The user that you want to update does not exist"));
        UserDB modificationPerson = userRepository.findByUsername(principal.getName()).get();

        userDB.setAge(dto.getAge());
        userDB.setHeight(dto.getHeight());
        userDB.setWeight(dto.getWeight());
        userDB.setGender(dto.getGender());
        userDB.setModificationPerson(modificationPerson);
        userDB.setModificationDate(LocalDateTime.now());

        return userDB;
    }

    @Override
    public UserDB updateUserData(ObjectId id, UpdateUserDataDTO dto, Principal principal){
        UserDB userDB = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("The user that you want to update does not exist"));
        UserDB modificationPerson = userRepository.findByUsername(principal.getName()).get();

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
        UserDB userDB = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("The user that you want to update does not exist"));
        UserDB modificationPerson = userRepository.findByUsername(principal.getName()).get();

        LocalDateTime time = LocalDateTime.now();

        userDB.setPassword(passwordEncoder.encode(dto.getPassword()));
        userDB.setPasswordDate(time);
        userDB.setModificationPerson(modificationPerson);
        userDB.setModificationDate(time);

        return userDB;
    }

    @Override
    public void deleteUser(ObjectId id){
        UserDB userDB = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("The user that you want to delete does not exist"));
        assetsRepository.delete(userDB.getAssets());
        userRepository.deleteById(id);
    }

}
