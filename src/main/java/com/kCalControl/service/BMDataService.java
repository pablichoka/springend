package com.kCalControl.service;

import com.kCalControl.dto.update.UpdateBMDataDTO;
import com.kCalControl.dto.update.UpdatePersonalDataDTO;
import com.kCalControl.model.BMData;
import org.bson.types.ObjectId;

public interface BMDataService {
    BMData returnBMDataLoggedUser();

    BMData returnBMDataByUserDBId(ObjectId id);

    BMData saveData(UpdatePersonalDataDTO personalDataDTO);

    BMData saveCalc(UpdateBMDataDTO dto);

    void calculateBaseBM(BMData bmData);

    void calculateFinalBM(BMData bmData, String dietType, Integer numDaysEx);
}
