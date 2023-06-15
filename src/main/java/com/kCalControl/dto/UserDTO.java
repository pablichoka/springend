package com.kCalControl.dto;

import com.kCalControl.model.Assets;
import com.kCalControl.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private ObjectId id;
    private String username;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String password;
    private Integer age;
    private Double weight;
    private Assets assets;
    private List<Role> roles;

}
