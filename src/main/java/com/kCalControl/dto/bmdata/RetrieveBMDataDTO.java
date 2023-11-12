package com.kCalControl.dto.bmdata;

import com.kCalControl.model.BMData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetrieveBMDataDTO {

    Integer age;
    Double weight;
    Integer height;
    String gender;
    Double baseBM;
    Integer numDaysEx;
    String dietType;
    Double totalBM;

    public RetrieveBMDataDTO(BMData bmData){
        age = bmData.getAge();
        weight = bmData.getWeight();
        height = bmData.getHeight();
        gender = bmData.getGender();
        baseBM = bmData.getBaseBm();
        numDaysEx = bmData.getNumDaysEx();
        dietType = bmData.getDietType();
        totalBM = bmData.getTotalBm();
    }

}
