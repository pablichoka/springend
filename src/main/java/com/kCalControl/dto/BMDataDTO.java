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

    private Integer numDaysEx;
    private String dietType;
}
