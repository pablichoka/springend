package com.kCalControl.model.IdClases;

import com.kCalControl.model.Role;
import com.kCalControl.model.UserDB;
import com.kCalControl.model.UserRole;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

public class UserRoleId implements Serializable {

    private UserDB userDB;
    private Role role;

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
