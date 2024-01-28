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
    private String accessToken;
    private Date tokenExpiry;
    private String refreshToken;
    private Date refreshTokenExpiry;
    private String roleName;

}
