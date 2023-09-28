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
public class UpdatePersonalDataDTO {

    private Double weight;
    private Integer age;
    private Integer height;
    private String gender;

}
