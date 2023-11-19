package com.kCalControl.dto.user;

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
    private String name;
    private String mobile;
    private String role; //format should be (ROLENAME1, ROLENAME2, ROLENAME3)
}
