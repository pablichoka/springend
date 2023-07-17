package com.kCalControl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "basalMet")
public class BMData {

    @Id
    private ObjectId id;
    @DBRef
    @Field("userBM.userAssoc")
    private UserDB userAssoc;
    @Field("userDB.age")
    private Integer age;
    @Field("userDB.weight")
    private Double weight;
    @Field("userDB.height")
    private Integer height;
    @Field("userDB.gender")
    private String gender;
    @Field("userBM.baseBM")
    private Double baseBM;
    @Field("userBM.numDaysEx")
    private Integer numDaysEx;
    @Field("userBM.dietType")
    private String dietType;
    @Field("userBM.totalBM")
    private Double totalBM;

    public String getUsername() {
        return getUserAssoc().getUsername();
    }
    public ObjectId getUserId() {
        return getUserAssoc().getId();
    }

    public BMData(Integer age, Double weight, Integer height, String gender, Double baseBM, Integer numDaysEx, String dietType, Double totalBM) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.baseBM = baseBM;
        this.numDaysEx = numDaysEx;
        this.dietType = dietType;
        this.totalBM = totalBM;
    }
}
