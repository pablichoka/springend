package com.kCalControl.model;

import com.kCalControl.model.IdClases.UserRoleId;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_role")
@IdClass(UserRoleId.class)
public class UserRole {

    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    private UserDB userDB;

    @Id
    @ManyToOne
    @JoinColumn(name = "role_name")
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

    public String getRoleName(){
        return role.getRole();
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(userDB, userRole.userDB) && Objects.equals(role, userRole.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userDB, role);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userDB=" + userDB +
                ", role=" + role +
                '}';
    }
}
