package com.kCalControl.dto;

import com.kCalControl.model.BMData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BMDataDTO {

    private Integer age;
    private Integer height;
    private Double weight;
    private String gender;
    private Double baseBM;
    private Integer numDaysEx;
    private String dietType;
    private Double totalBM;

    public BMData BMDataDTO2UserBM(BMDataDTO dto){
        BMData BMData = new BMData();
        BMData.setAge(dto.getAge());
        BMData.setHeight(dto.getHeight());
        BMData.setWeight(dto.getWeight());
        BMData.setGender(dto.getGender());
        BMData.setNumDaysEx(dto.getNumDaysEx());
        BMData.setDietType(dto.getDietType());

        return BMData;
    }

}
