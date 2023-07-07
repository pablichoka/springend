package com.kCalControl.dto;

import com.kCalControl.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewUserDTO {

    private String username;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String password;
    private Role role;

}
