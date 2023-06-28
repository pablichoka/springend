package com.kCalControl.service.impl;

import com.kCalControl.model.Assets;
import com.kCalControl.model.UserDB;
import com.kCalControl.repository.AssetsRepository;
import com.kCalControl.repository.RoleRepository;
import com.kCalControl.repository.UserRepository;
import com.kCalControl.service.UserDBService;
import com.kCalControl.service.dto.NewUserDTO;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserDBServiceImpl implements UserDBService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AssetsRepository assetsRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public ObjectId newUser(ObjectId creationPersonId, NewUserDTO dto){
        UserDB creationPerson = userRepository.findById(creationPersonId)
                .orElseThrow(() -> new UsernameNotFoundException("The creator does not exist"));

        UserDB userDB = new UserDB();
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
        userDB.setRole(roleRepository.findByRoleName("ADMIN").get());

        var user = userRepository.save(userDB);
        var asset = assetsRepository.save(assets);

        return user.getId();

    }

}
