package com.kCalControl.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserDataDTO {

    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private ObjectId updaterId;

}
