package com.kCalControl.service.impl;

import com.kCalControl.dto.BMDataDTO;
import com.kCalControl.dto.UpdatePersonalDataDTO;
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
        BMData bmData = BMDataRepository.findByUserAssoc_Id(userDBService.returnLoggedUser().getId());
        return bmData;
    }
    @Override
    public BMData returnBMDataByUserDBId(ObjectId id){
        BMData bmData = BMDataRepository.findByUserAssoc_Id(id);
        return bmData;
    }

    @Override
    public BMData saveData(ObjectId id, UpdatePersonalDataDTO personalDataDTO){
        BMData bmData = BMDataRepository.findByUserAssoc_Id(id);
        bmData.setAge(personalDataDTO.getAge());
        bmData.setHeight(personalDataDTO.getHeight());
        bmData.setWeight(personalDataDTO.getWeight());
        bmData.setGender(personalDataDTO.getGender());
        return bmData;
    }

    @Override
    public BMData saveCalc(ObjectId id, BMDataDTO dto){
        BMData bmData = BMDataRepository.findByUserAssoc_Id(id);
        bmData.setDietType(dto.getDietType());
        bmData.setNumDaysEx(dto.getNumDaysEx());
        return bmData;
    }

    @Override
    public Double calculateBaseBM(BMData bmData) {
        Double baseBM;
        switch (bmData.getGender()) {
            case "Male":
                baseBM = ((66 + (13.7 * bmData.getWeight())) + ((5 * bmData.getHeight()) - (6.8 * bmData.getAge())));
                break;
            case "Female":
                baseBM = ((655 + (9.6 * bmData.getWeight())) + ((1.8 * bmData.getHeight()) - (4.7 * bmData.getAge())));
                break;
            default: baseBM = 0.0;
                break;
        }
        return baseBM;
    }
    @Override
    public Double calculateFinalBM(BMData bmData, String dietType, Integer numDaysEx) {
        Double percentageOfkCal;
        Double exFactor;
        Double totalBM;
        switch (dietType) {
            case "def":
                percentageOfkCal = 0.85;
                break;
            case "main":
                percentageOfkCal = 1.00;
                break;
            case "gain":
                percentageOfkCal = 1.15;
                break;
            default:
                percentageOfkCal = 0.00;
                break;
        }
        switch (numDaysEx) {
            case 0:
                exFactor = 1.2;
                break;
            case 1:
                exFactor = 1.26;
                ;
                break;
            case 2:
                exFactor = 1.32;
                ;
                break;
            case 3:
                exFactor = 1.37;
                ;
                break;
            case 4:
                exFactor = 1.46;
                ;
                break;
            case 5:
                exFactor = 1.55;
                ;
                break;
            case 6:
                exFactor = 1.72;
                ;
                break;
            case 7:
                exFactor = 1.85;
                ;
                break;
            default:
                exFactor = 0.0;
                break;
        }
        totalBM = calculateBaseBM(bmData)*percentageOfkCal*exFactor;
        return totalBM;
        }
}

