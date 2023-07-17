package com.kCalControl.service.impl;

import com.kCalControl.dto.BMDataDTO;
import com.kCalControl.model.BMData;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.service.BMDataService;
import com.kCalControl.service.UserDBService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BMDataServiceImpl implements BMDataService {
    @Autowired
    BMDataRepository BMDataRepository;
    @Autowired
    UserDBService userDBService;

    @Override
    public BMData returnBMLoggedUser() {
        Optional<BMData> bmDataOptional = BMDataRepository.findByUserAssoc_Id(userDBService.returnLoggedUser().getId());
        if(bmDataOptional.isPresent()){
            return bmDataOptional.get();
        }else {
            return new BMData(0, 0.0, 0, "", 0.0, 0, "", 0.0);
        }

    }
    @Override
    public BMData returnUserBMByUserDBId(ObjectId id){
        Optional<BMData> bmDataOptional = BMDataRepository.findByUserAssoc_Id(id);
        if(bmDataOptional.isPresent()){
            return bmDataOptional.get();
        }else {
            return new BMData(0, 0.0, 0, "", 0.0, 0, "", 0.0);
        }
    }

    @Override
    public void saveData(BMDataDTO dto){
        BMData BMData = dto.BMDataDTO2UserBM(dto);
        BMData.setUserAssoc(userDBService.returnLoggedUser());
        BMDataRepository.save(BMData);
    }
    @Override
    public Double calculateBaseBM() {
        BMData user = BMDataRepository.findByUserAssoc_Id(userDBService.returnLoggedUser().getId()).get();
        Double baseBM;
        switch (user.getGender()) {
            case "Male":
                baseBM = ((66 + (13.7 * user.getWeight())) + ((5 * user.getHeight()) - (6.8 * user.getAge())));
                break;
            case "Female":
                baseBM = ((655 + (9.6 * user.getWeight())) + ((1.8 * user.getHeight()) - (4.7 * user.getAge())));
                break;
            default: baseBM = 0.0;
                break;
        }
        return baseBM;
    }
    @Override
    public Double calculateFinalBM(String dietType, Integer numDaysEx) {
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
        totalBM = calculateBaseBM()*percentageOfkCal*exFactor;
        return totalBM;
        }
}

