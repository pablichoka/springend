package com.kCalControl.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class UserDB {

    @Id
    private ObjectId id;
    @Field("userDB.username")
    private String username;
    @Field("userDB.firstName")
    private String firstName;
    @Field("userDB.lastName")
    private String lastName;
    @Field("userDB.mobile")
    private String mobile;
    @Field("userDB.email")
    private String email;
    @Field("userDB.password")
    private String password;
    @Field("userDB.passwordDate")
    private LocalDateTime passwordDate;

    @DBRef
    @Field("userDB.role")
    private Role role;

    @DBRef
    @Field("userDB.assets")
    private Assets assets;

    @DBRef
    @Field("userDB.bmData")
    private BMData bmData;

    public UserDB(String username, String firstName, String lastName, String mobile, String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }

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

    public String getRoleName() {
        return getRole().getRoleName();
    }

    public void setRoleName(String roleName) {
        getRole().setRoleName(roleName);
    }

    public UserDB getCreationPerson() {
        return getAssets().getCreationPerson();
    }

    public LocalDateTime getCreationDate() {
        return getAssets().getCreationDate();
    }

    public UserDB getModificationPerson() {
        return getAssets().getModificationPerson();
    }

    public LocalDateTime getModificationDate() {
        return getAssets().getModificationDate();
    }

    public void setCreationPerson(UserDB creationPerson) {
        getAssets().setCreationPerson(creationPerson);
    }

    public void setCreationDate(LocalDateTime creationDate) {
        getAssets().setCreationDate(creationDate);
    }

    public void setModificationPerson(UserDB modificationPerson) {
        getAssets().setModificationPerson(modificationPerson);
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        getAssets().setModificationDate(modificationDate);
    }
}
