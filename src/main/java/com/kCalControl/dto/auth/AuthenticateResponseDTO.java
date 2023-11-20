package com.kCalControl.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateResponseDTO {

    private String id;
    private String token;
    private Date tokenExpiry;
    private String roleName;

}
