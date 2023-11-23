package com.kCalControl.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String mobile;
    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "credentials_id", referencedColumnName = "id")
    private Credentials credentials;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_name"))
    private Set<Role> roles;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "bm_data_id", referencedColumnName = "id")
    private BMData bmData;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "assets_id", referencedColumnName = "id")
    private Assets assets;

    public User(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public String getUsername() {
        return getCredentials().getUsername();
    }

    public String getEmail() {
        return getCredentials().getEmail();
    }

    public String getPassword() {
        return getCredentials().getPassword();
    }

    public Date getPasswordDate() {
        return getCredentials().getPasswordDate();
    }

    public Integer getCreationPerson() {
        return getAssets().getCreationPerson();
    }

    public Date getCreationDate() {
        return getAssets().getCreationDate();
    }

    public Integer getModificationPerson() {
        return getAssets().getModificationPerson();
    }

    public Date getModificationDate() {
        return getAssets().getModificationDate();
    }

    public void setModificationDate(Date modificationDate) {
        getAssets().setModificationDate(modificationDate);
    }

    public void setModificationPerson(Integer modificationPerson) {
        getAssets().setModificationPerson(modificationPerson);
    }

    public ObjectNode toJson() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode userJson = mapper.createObjectNode();
        userJson.put("id", getId());
        userJson.put("name", getName());
        userJson.put("mobile", getMobile());
        userJson.put("creationPerson", getCreationPerson());
        userJson.put("creationDate", getCreationDate().toString());
        userJson.put("modificationPerson", getModificationPerson());
        userJson.put("modificationDate", getModificationDate().toString());
        return userJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(mobile, user.mobile) && Objects.equals(name, user.name) && Objects.equals(credentials, user.credentials) && Objects.equals(roles, user.roles) && Objects.equals(bmData, user.bmData) && Objects.equals(assets, user.assets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobile, name);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", mobile='" + mobile + '\'' + ", name='" + name + '\'' + '}';
    }
}
