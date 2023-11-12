package com.kCalControl.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Users")
public class UserDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String mobile;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String username;

    private String name;

    @Column(nullable = false)
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime passwordDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "User_Role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bm_data_id", referencedColumnName = "id")
    private BMData bmData;

    @OneToOne(mappedBy = "users")
    private Assets assets;

    public UserDB(String username, String name, String mobile, String email, String password) {
        this.username = username;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }

    public Integer getCreationPerson() {
        return getAssets().getCreationPerson();
    }

    public LocalDateTime getCreationDate() {
        return getAssets().getCreationDate();
    }

    public Integer getModificationPerson() {
        return getAssets().getModificationPerson();
    }

    public LocalDateTime getModificationDate() {
        return getAssets().getModificationDate();
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        getAssets().setModificationDate(modificationDate);
    }

    public void setModificationPerson(Integer modificationPerson) {
        getAssets().setModificationPerson(modificationPerson);
    }

    public void setCreationDate(LocalDateTime creationDate) {
        getAssets().setCreationDate(creationDate);
    }

    public void setCreationPerson(Integer creationPerson) {
        getAssets().setCreationPerson(creationPerson);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDB userDB)) return false;
        return Objects.equals(id, userDB.id) && Objects.equals(mobile, userDB.mobile) && Objects.equals(email, userDB.email) && Objects.equals(username, userDB.username) && Objects.equals(name, userDB.name) && Objects.equals(password, userDB.password) && Objects.equals(passwordDate, userDB.passwordDate) && Objects.equals(roles, userDB.roles) && Objects.equals(bmData, userDB.bmData) && Objects.equals(assets, userDB.assets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mobile, email, username, name, password, passwordDate, roles, bmData, assets);
    }
}
