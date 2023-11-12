package com.kCalControl.dto.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kCalControl.model.UserDB;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveUserDTO {

    private String username;
    private String name;
    private String mobile;
    private String email;

    public RetrieveUserDTO(UserDB userDB){
        username = userDB.getUsername();
        name = userDB.getName();
        mobile = userDB.getMobile();
        email = userDB.getEmail();
    }

    public String toJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode object2JSON = objectMapper.createObjectNode();
        object2JSON.put("username", this.username);
        object2JSON.put("name", this.name);
        object2JSON.put("mobile", this.mobile);
        object2JSON.put("email", this.email);
        return object2JSON.toString();
    }
}
