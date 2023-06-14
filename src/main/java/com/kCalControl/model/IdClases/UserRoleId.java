package com.kCalControl.model.IdClases;

import com.kCalControl.model.Role;
import com.kCalControl.model.UserDB;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

@Document(collection = "user_role")
public class UserRoleId implements Serializable {

    @Field("user")
    private UserDB userDB;

    @Field("role")
    private Role role;

    public UserRoleId() {
    }

    public UserRoleId(UserDB userDB, Role role) {
        this.userDB = userDB;
        this.role = role;
    }

    public UserDB getUserDB() {
        return userDB;
    }

    public void setUserDB(UserDB userDB) {
        this.userDB = userDB;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleId that = (UserRoleId) o;
        return Objects.equals(userDB, that.userDB) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userDB, role);
    }

    @Override
    public String toString() {
        return "UserRoleId{" +
                "userDB=" + userDB +
                ", role=" + role +
                '}';
    }
}
