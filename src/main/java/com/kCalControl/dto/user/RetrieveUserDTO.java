package com.kCalControl.dto.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kCalControl.model.Assets;
import com.kCalControl.model.User;
import lombok.AllArgsConstructor;
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
    private ObjectNode assets;

    public RetrieveUserDTO(User user, ObjectNode assets){
        username = user.getUsername();
        name = user.getName();
        mobile = user.getMobile();
        email = user.getEmail();
        this.assets = assets;
    }

    public String toJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode object2JSON = objectMapper.createObjectNode();
        object2JSON.put("username", this.username);
        object2JSON.put("name", this.name);
        object2JSON.put("mobile", this.mobile);
        object2JSON.put("email", this.email);
        object2JSON.put("assets", this.assets.toString());
        return object2JSON.toString();
    }
}
