package com.kCalControl.service;

import com.kCalControl.dto.bmdata.UpdateBMDataDTO;
import com.kCalControl.dto.bmdata.UpdatePersonalDataDTO;
import com.kCalControl.model.BMData;
import org.bson.types.ObjectId;

public interface BMDataService {
    BMData returnBMDataByUserDBId(ObjectId id);

    BMData saveData(ObjectId id, UpdatePersonalDataDTO personalDataDTO);

    BMData saveCalc(ObjectId id, UpdateBMDataDTO dto);

    void calculateBaseBM(BMData bmData);

    void calculateFinalBM(BMData bmData, String dietType, Integer numDaysEx);
}
