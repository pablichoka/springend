package com.kCalControl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "role")
public class Role {

    public static final String ADMIN_ID = "649148f8db4bc6efbe99b47a";
    private static final String USER_ID = "64918e9adb4bc6efbe99b489";

    @Id
    private ObjectId id;
    @Field("roleName")
    private String roleName;
}
