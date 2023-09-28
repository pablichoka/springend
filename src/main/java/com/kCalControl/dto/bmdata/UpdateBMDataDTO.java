package com.kCalControl.dto.bmdata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBMDataDTO {

    private Integer numDaysEx;
    private String dietType;
}
