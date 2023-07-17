package com.kCalControl.model;

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
    @Field("userDB.userBM")
    private BMData bmData;

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
