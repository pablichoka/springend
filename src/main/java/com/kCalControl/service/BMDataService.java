package com.kCalControl.service;

import com.kCalControl.dto.BMDataDTO;
import com.kCalControl.dto.UpdatePersonalDataDTO;
import com.kCalControl.model.BMData;
import org.bson.types.ObjectId;

public interface BMDataService {
    BMData returnBMDataLoggedUser();

    BMData returnBMDataByUserDBId(ObjectId id);

    BMData saveData(ObjectId id, UpdatePersonalDataDTO personalDataDTO);

    BMData saveCalc(ObjectId id, BMDataDTO dto);

    Double calculateBaseBM(BMData bmData);

    Double calculateFinalBM(BMData bmData, String dietType, Integer numDaysEx);
}
