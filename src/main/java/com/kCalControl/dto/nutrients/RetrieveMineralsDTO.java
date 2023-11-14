package com.kCalControl.dto.nutrients;

import com.kCalControl.model.Minerals;
import com.kCalControl.model.Nutrients;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetrieveMineralsDTO {

    Double calcium;
    Double copper;
    Double iron;
    Double magnesium;
    Double phosphorus;
    Double potassium;
    Double sodium;
    Double zinc;

    public RetrieveMineralsDTO(Minerals minerals){
        calcium = minerals.getCalcium();
        copper = minerals.getCopper();
        iron = minerals.getIron();
        magnesium = minerals.getMagnesium();
        phosphorus = minerals.getPhosphorus();
        potassium = minerals.getPotassium();
        sodium = minerals.getSodium();
        zinc = minerals.getZinc();
    }

}
