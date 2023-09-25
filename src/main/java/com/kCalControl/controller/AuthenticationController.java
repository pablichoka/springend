package com.kCalControl.controller;

import com.kCalControl.dto.NewUserDTO;
import com.kCalControl.dto.auth.AuthenticateRequestDTO;
import com.kCalControl.dto.auth.AuthenticateResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public interface AuthenticationController {
    @PostMapping("authenticate")
    public ResponseEntity<AuthenticateResponseDTO> authenticate(@RequestBody AuthenticateRequestDTO request);
    @PostMapping("signup")
    void createNormalUser(@RequestBody NewUserDTO dto);
}
