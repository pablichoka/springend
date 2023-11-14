package com.kCalControl.controller.impl;

import com.kCalControl.config.TokenManager;
import com.kCalControl.controller.AuthenticationController;
import com.kCalControl.dto.auth.AuthenticateRequestDTO;
import com.kCalControl.dto.auth.AuthenticateResponseDTO;
import com.kCalControl.repository.UserDBRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthenticationControllerImpl implements AuthenticationController {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    UserDBRepository userDBRepository;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    TokenManager tokenManager;

    @Override
    public ResponseEntity<AuthenticateResponseDTO> authenticate(@RequestBody AuthenticateRequestDTO request) {

        var username = request.getUsername();
        var findByUsername = userDBRepository.findByUsername(username);
        if (findByUsername.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");

        }
        var user = findByUsername.get();

        var matches = encoder.matches(request.getPassword(), user.getPassword());
        if (!matches) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        var userDB_id = user.getId().toString();
        var roleName = user.getRoles();
        var token = this.tokenManager.generateJwtToken(userDB_id, roleName.toString());
        logger.debug("Generating token {} for {}", token, username);

        var response = new AuthenticateResponseDTO(userDB_id, token, user.getRoles().toString());
        return ResponseEntity.ok(response);
    }
}
