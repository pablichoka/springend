package com.kCalControl.model;

import com.kCalControl.model.IdClases.UserRoleId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "user_role")
public class UserRole {

    @Id
    private UserRoleId id;

}
