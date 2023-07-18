package com.kCalControl.controller.impl;

import com.kCalControl.controller.BMDataController;
import com.kCalControl.dto.BMDataDTO;
import com.kCalControl.dto.UpdatePersonalDataDTO;
import com.kCalControl.model.BMData;
import com.kCalControl.repository.BMDataRepository;
import com.kCalControl.service.BMDataService;
import com.kCalControl.service.UserDBService;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class BMDataControllerImpl implements BMDataController {
    @Autowired
    UserDBService userDBService;
    @Autowired
    BMDataService BMDataService;
    @Autowired
    BMDataRepository BMDataRepository;
    @Override
    public String bmCalculator(Model model) {
        model.addAttribute("user", userDBService.returnLoggedUser());
        BMData bmData = BMDataService.returnBMDataLoggedUser();
        BMDataService.calculateBaseBM(bmData);
        BMDataService.calculateFinalBM(bmData, bmData.getDietType(), bmData.getNumDaysEx());
        model.addAttribute("BMData", bmData);
        model.addAttribute("BMDataDTO", new BMDataDTO());
        model.addAttribute("personalDataDTO", new UpdatePersonalDataDTO());
        return "/views/calcBM";
    }

    @Override
    public void updateBMCalc(ObjectId id, BMDataDTO dto, HttpServletResponse httpServletResponse) {
        BMData bmData = BMDataService.saveCalc(id, dto);
        BMDataService.calculateBaseBM(bmData);
        BMDataService.calculateFinalBM(bmData, dto.getDietType(), dto.getNumDaysEx());
        BMDataRepository.save(bmData);
        httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @Override
    public void updateBMData(ObjectId id, UpdatePersonalDataDTO dto, HttpServletResponse httpServletResponse) {
        BMData bmData = BMDataService.saveData(id,dto);
        BMDataRepository.save(bmData);
        httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
