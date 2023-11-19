package com.kCalControl.controller.impl;

import com.kCalControl.config.TokenManager;
import com.kCalControl.controller.AuthenticationController;
import com.kCalControl.dto.auth.AuthenticateRequestDTO;
import com.kCalControl.dto.auth.AuthenticateResponseDTO;
import com.kCalControl.model.Role;
import com.kCalControl.repository.CredentialsRepository;
import com.kCalControl.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthenticationControllerImpl implements AuthenticationController {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    CredentialsRepository credentialsRepository;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    TokenManager tokenManager;

    @Override
    public ResponseEntity<AuthenticateResponseDTO> authenticate(@RequestBody AuthenticateRequestDTO request) {

        var username = request.getUsername();
        var findByUsername = credentialsRepository.findByUsername(username);
        if (findByUsername.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");

        }
        var user = findByUsername.get();

        var matches = encoder.matches(request.getPassword(), user.getPassword());
        if (!matches) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        var userDB_id = user.getId().toString();
        List<Role> roles = user.getRoles().stream().toList();
        List<String> roleNames = roles.stream().map(Role::getRoleName).toList();
        var token = this.tokenManager.generateJwtToken(userDB_id, roleNames.toString());
        logger.debug("Generating token {} for {}", token, username);

//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (Role role : roles){
//            logger.debug("Este es el role que se añade: " + role.getRoleName());
//            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
//        }
//        new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
        var response = new AuthenticateResponseDTO(userDB_id, token, roleNames.toString());
        return ResponseEntity.ok(response);
    }
}
