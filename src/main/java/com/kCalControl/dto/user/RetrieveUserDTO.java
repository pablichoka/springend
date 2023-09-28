package com.kCalControl.dto.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetrieveUserDTO {

    private String username;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;

    public String toJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode object2JSON = objectMapper.createObjectNode();
        object2JSON.put("username", this.username);
        object2JSON.put("firstName", this.firstName);
        object2JSON.put("lastName", this.lastName);
        object2JSON.put("mobile", this.mobile);
        object2JSON.put("email", this.email);
        return object2JSON.toString();
    }
}
