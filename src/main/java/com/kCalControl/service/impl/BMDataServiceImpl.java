package com.kCalControl.service.impl;

import com.kCalControl.dto.update.UpdateBMDataDTO;
import com.kCalControl.dto.update.UpdatePersonalDataDTO;
import com.kCalControl.model.BMData;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.service.BMDataService;
import com.kCalControl.service.UserDBService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class BMDataServiceImpl implements BMDataService {

    private static final Logger logger = Logger.getLogger(BMDataServiceImpl.class.getName());
    @Autowired
    BMDataRepository BMDataRepository;
    @Autowired
    UserDBService userDBService;

    @Override
    public BMData returnBMDataLoggedUser() {
        return userDBService.returnLoggedUser().getBmData();
    }

    @Override
    public BMData returnBMDataByUserDBId(ObjectId id) {
        return BMDataRepository.findByUserAssoc_Id(id);
    }

    @Override
    public BMData saveData(UpdatePersonalDataDTO personalDataDTO) {
        BMData bmData = userDBService.returnLoggedUser().getBmData();
        bmData.setAge(personalDataDTO.getAge());
        bmData.setHeight(personalDataDTO.getHeight());
        bmData.setWeight(personalDataDTO.getWeight());
        bmData.setGender(personalDataDTO.getGender());
        return bmData;
    }

    @Override
    public BMData saveCalc(UpdateBMDataDTO dto) {
        BMData bmData = userDBService.returnLoggedUser().getBmData();
        bmData.setDietType(dto.getDietType());
        bmData.setNumDaysEx(dto.getNumDaysEx());
        return bmData;
    }

    @Override
    public void calculateBaseBM(BMData bmData) {
        double baseBM = switch (bmData.getGender()) {
            case "Male" -> ((66 + (13.7 * bmData.getWeight())) + ((5 * bmData.getHeight()) - (6.8 * bmData.getAge())));
            case "Female" ->
                    ((655 + (9.6 * bmData.getWeight())) + ((1.8 * bmData.getHeight()) - (4.7 * bmData.getAge())));
            default -> 0.0;
        };
        bmData.setBaseBM(baseBM);
        BMDataRepository.save(bmData);
    }

    @Override
    public void calculateFinalBM(BMData bmData, String dietType, Integer numDaysEx) {
        Double percentageOfkCal;
        Double exFactor;
        double totalBM;
        percentageOfkCal = switch (dietType) {
            case "Definition" -> 0.85;
            case "Maintain" -> 1.00;
            case "Gaining" -> 1.15;
            default -> 0.00;
        };
        switch (numDaysEx) {
            case 0 -> exFactor = 1.2;
            case 1 -> {
                exFactor = 1.26;
                ;
            }
            case 2 -> {
                exFactor = 1.32;
                ;
            }
            case 3 -> {
                exFactor = 1.37;
                ;
            }
            case 4 -> {
                exFactor = 1.46;
                ;
            }
            case 5 -> {
                exFactor = 1.55;
                ;
            }
            case 6 -> {
                exFactor = 1.72;
                ;
            }
            case 7 -> {
                exFactor = 1.85;
                ;
            }
            default -> exFactor = 0.0;
        }
        totalBM = bmData.getBaseBM() * percentageOfkCal * exFactor;
        bmData.setTotalBM(totalBM);
        BMDataRepository.save(bmData);
    }
}

