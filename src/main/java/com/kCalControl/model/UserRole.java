package com.kCalControl.model;

import com.kCalControl.model.IdClases.UserRoleId;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_role")
public class UserRole {

    @EmbeddedId
    private UserRoleId id;

    public UserRole() {
    }

    public UserRole(UserRoleId id) {
        this.id = id;
    }

    public UserRoleId getId() {
        return id;
    }

    public void setId(UserRoleId id) {
        this.id = id;
    }

    @Transient
    public UserDB getUserDB() {
        return getId().getUserDB();
    }

    @Transient
    public String getRole() {
        return getId().getRole().getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole userRole)) return false;
        return Objects.equals(id, userRole.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                '}';
    }
}
