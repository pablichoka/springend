package com.kCalControl.model;

import com.kCalControl.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
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

    private String username;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String password;
    private LocalDateTime passwordDate;
    private Integer age;
    private Double weight;
    @Field("assets")
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

}
