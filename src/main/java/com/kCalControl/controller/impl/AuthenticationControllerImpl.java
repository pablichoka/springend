package com.kCalControl.controller.impl;

import com.kCalControl.config.TokenManager;
import com.kCalControl.controller.AuthenticationController;
import com.kCalControl.dto.auth.AuthenticateRequestDTO;
import com.kCalControl.dto.auth.AuthenticateResponseDTO;
import com.kCalControl.exceptions.NetworkException;
import com.kCalControl.model.Role;
import com.kCalControl.repository.CredentialsRepository;
import com.kCalControl.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.kCalControl.config.TokenManager.TOKEN_VALIDITY;

@Service
public class AuthenticationControllerImpl implements AuthenticationController {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    UserRepository userRepository;
    CredentialsRepository credentialsRepository;
    BCryptPasswordEncoder encoder;
    TokenManager tokenManager;

    @Autowired
    public AuthenticationControllerImpl(UserRepository userRepository, CredentialsRepository credentialsRepository,
                                        BCryptPasswordEncoder encoder, TokenManager tokenManager) {
        this.userRepository = userRepository;
        this.credentialsRepository = credentialsRepository;
        this.encoder = encoder;
        this.tokenManager = tokenManager;
    }

    @Override
    public ResponseEntity<AuthenticateResponseDTO> authenticate(@RequestBody AuthenticateRequestDTO request) {

        var username = request.getUsername();
        var findByUsername = credentialsRepository.findByUsername(username);
        if (findByUsername.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");

        }
        var credentials = findByUsername.get();

        var matches = encoder.matches(request.getPassword(), credentials.getPassword());
        if (!matches) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        Date expiryDate = new Date(System.currentTimeMillis() + TOKEN_VALIDITY);

        var user = userRepository.findByCredentialsId(credentials.getId()).orElseThrow(() -> new NetworkException("User not found", HttpStatus.NOT_FOUND));
        var user_id = user.getId().toString();
        List<Role> roles = user.getRoles().stream().toList();
        List<String> roleNames = roles.stream().map(Role::getRoleName).toList();
        var token = this.tokenManager.generateJwtToken(user_id, roleNames.toString(), expiryDate);
        logger.debug("Generating token {} for {}", token, username);

        var response = new AuthenticateResponseDTO(user_id, token, expiryDate, roleNames.toString());
        return ResponseEntity.ok(response);
    }
}
