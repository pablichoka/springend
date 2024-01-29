package com.kCalControl.dto.credentials;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCredentialsDTO {

    private String username;
    private String email;
    private String password;
}
