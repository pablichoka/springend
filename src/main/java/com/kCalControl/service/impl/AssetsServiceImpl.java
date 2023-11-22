package com.kCalControl.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kCalControl.exceptions.NetworkException;
import com.kCalControl.model.Assets;
import com.kCalControl.model.User;
import com.kCalControl.repository.UserRepository;
import com.kCalControl.service.AssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AssetsServiceImpl implements AssetsService {

    UserRepository userRepository;

    @Autowired
    public AssetsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public ObjectNode returnAssets(Assets assets) {
        User creationPerson = userRepository.findById(assets.getCreationPerson()).orElseThrow(() -> new NetworkException("User not found", HttpStatus.NOT_FOUND));
        User modificationPerson = userRepository.findById(assets.getModificationPerson()).orElseThrow(() -> new NetworkException("User not found", HttpStatus.NOT_FOUND));
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("creationPerson", creationPerson.getEmail());
        node.put("creationDate", assets.getCreationDate().toString());
        node.put("modificationPerson", modificationPerson.getEmail());
        node.put("modificationDate", assets.getModificationDate().toString());
        return node;
    }
}