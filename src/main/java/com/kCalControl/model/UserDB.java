package com.kCalControl.model;

import com.kCalControl.dto.UserDTO;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@Document(collection = "users")
public class UserDB {

    @Id
    private Integer id;

    private String username;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String password;
    private LocalDateTime passwordDate;
    private Integer age;
    private Double weight;
    private Assets assets;

    public UserDTO UserDB2UserDTO() {

        UserDTO userDTO = new UserDTO();

        userDTO.setId(this.getId());
        userDTO.setUsername(this.getUsername());
        userDTO.setFirstName(this.getFirstName());
        userDTO.setLastName(this.getLastName());
        userDTO.setEmail(this.getEmail());
        userDTO.setMobile(this.getMobile());
        userDTO.setAge(this.getAge());
        userDTO.setWeight(this.getWeight());

        return userDTO;
    }

    public UserDB() {
    }

    public UserDB(String username, String firstName, String lastName, String mobile, String email, String password, Integer age, Double weight) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.age = age;
        this.weight = weight;
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

    public void setModificationPerson(UserDB modificationPerson) {
        getAssets().setModificationPerson(modificationPerson);
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        getAssets().setModificationDate(modificationDate);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getPasswordDate() {
        return passwordDate;
    }

    public void setPasswordDate(LocalDateTime passwordDate) {
        this.passwordDate = passwordDate;
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDB userDB)) return false;
        return Objects.equals(id, userDB.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UserDB{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordDate=" + passwordDate +
                ", age=" + age +
                ", weight=" + weight +
                ", assets=" + assets +
                '}';
    }
}
