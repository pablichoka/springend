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
    private Integer age;
    private Double weight;
    private Integer height;
    private String gender;
    private Double baseBM;
    private Integer numDaysEx;
    private String dietType;
    private Double totalBM;
    @DBRef
    private UserDB userAssoc;

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
