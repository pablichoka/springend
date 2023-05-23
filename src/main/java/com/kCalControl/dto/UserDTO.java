package com.kCalControl.dto;

import java.util.Objects;

public class UserDTO {

    private String username;
    private String f_name;
    private String l_name;
    private String mobile;
    private String email;
    private String password;
    private Integer age;
    private Double weight;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
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
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(username, userDTO.username) && Objects.equals(f_name, userDTO.f_name) && Objects.equals(l_name, userDTO.l_name) && Objects.equals(mobile, userDTO.mobile) && Objects.equals(email, userDTO.email) && Objects.equals(password, userDTO.password) && Objects.equals(age, userDTO.age) && Objects.equals(weight, userDTO.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, f_name, l_name, mobile, email, password, age, weight);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", f_name='" + f_name + '\'' +
                ", l_name='" + l_name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}
