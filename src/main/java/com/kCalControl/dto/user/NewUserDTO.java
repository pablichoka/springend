package com.kCalControl.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewUserDTO {
    private String firstName;
    private String lastName;
    private String mobile;
    private String role; //format should be (ROLENAME1, ROLENAME2, ROLENAME3)
    private String username;
    private String password;
    private String email;
}
