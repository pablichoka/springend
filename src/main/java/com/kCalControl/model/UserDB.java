package com.kCalControl.model;

import com.kCalControl.dto.UserDTO;
import lombok.*;
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
    @Field("userDB.age")
    private Integer age;
    @Field("userDB.weight")
    private Double weight;

    @DBRef
    @Field("userDB.role")
    private Role role;

    @Field("userDB.assets")
    private Assets assets;

    public UserDTO UserDB2UserDTO() {

        UserDTO userDTO = new UserDTO();

        userDTO.setId(this.getId());
        userDTO.setUsername(this.getUsername());
        userDTO.setFirstName(this.getFirstName());
        userDTO.setLastName(this.getLastName());
        userDTO.setEmail(this.getEmail());
        userDTO.setMobile(this.getMobile());
        userDTO.setRole(this.getRole());
        userDTO.setAge(this.getAge());
        userDTO.setWeight(this.getWeight());
        userDTO.setAssets(this.getAssets());

        return userDTO;
    }

    public UserDB(String username, String firstName, String lastName, String mobile, String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }

    public String getRoleName() {
        return getRole().getRoleName();
    }

    public void setRoleName(String roleName) {
        getRole().setRoleName(roleName);
    }

    public ObjectId getCreationPerson() {
        return getAssets().getCreationPerson();
    }

    public LocalDateTime getCreationDate() {
        return getAssets().getCreationDate();
    }

    public ObjectId getModificationPerson() {
        return getAssets().getModificationPerson();
    }

    public LocalDateTime getModificationDate() {
        return getAssets().getModificationDate();
    }

    public void setCreationPerson(ObjectId creationPerson) {
        getAssets().setCreationPerson(creationPerson);
    }

    public void setCreationDate(LocalDateTime creationDate) {
        getAssets().setCreationDate(creationDate);
    }

    public void setModificationPerson(ObjectId modificationPerson) {
        getAssets().setModificationPerson(modificationPerson);
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        getAssets().setModificationDate(modificationDate);
    }
}
