package com.kCalControl.controller.impl;

import com.kCalControl.controller.BMDataController;
import com.kCalControl.dto.BMDataDTO;
import com.kCalControl.model.BMData;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.service.BMDataService;
import com.kCalControl.service.UserDBService;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class BMDataControllerImpl implements BMDataController {
    @Autowired
    UserDBService userDBService;
    @Autowired
    BMDataService BMDataService;
    @Autowired
    BMDataRepository BMDataRepository;

    //TODO implement an endpoint to receive the data from the form, save it and return by ajax request the page with updated data
    //TODO create DTO for the form
    @Override
    public String bmCalculator(Model model) {
        model.addAttribute("user", userDBService.returnLoggedUser());
        model.addAttribute("userBM", BMDataService.returnBMLoggedUser());
        model.addAttribute("userBMDTO", new BMDataDTO());
        return "/views/calcBM";
    }

    @Override
    public void updateBMCalc(ObjectId id, BMDataDTO dto, HttpServletResponse httpServletResponse) {
        BMData BMData = BMDataService.returnUserBMByUserDBId(id);
        BMData.setDietType(dto.getDietType());
        BMData.setNumDaysEx(dto.getNumDaysEx());
        BMData.setBaseBM(BMDataService.calculateBaseBM());
        BMData.setTotalBM(BMDataService.calculateFinalBM(dto.getDietType(), dto.getNumDaysEx()));
        BMDataRepository.save(BMData);
        httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @Override
    public void updateBMData(ObjectId id, BMDataDTO dto, HttpServletResponse httpServletResponse) {
        BMData BMData = BMDataService.returnUserBMByUserDBId(id);
        BMData.setGender(dto.getGender());
        BMData.setHeight(dto.getHeight());
        BMData.setAge(dto.getAge());
        BMData.setWeight(dto.getWeight());
        BMDataRepository.save(BMData);
        httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
