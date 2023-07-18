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

    //TODO implement an endpoint to receive the data from the form, save it and return by ajax request the page with updated data
    //TODO create DTO for the form
    @Override
    public String bmCalculator(Model model) {
        model.addAttribute("user", userDBService.returnLoggedUser());
        model.addAttribute("BMData", BMDataService.returnBMDataLoggedUser());
        model.addAttribute("BMDataDTO", new BMDataDTO());
        model.addAttribute("personalDataDTO", new UpdatePersonalDataDTO());
        return "/views/calcBM";
    }

    @Override
    public void updateBMCalc(ObjectId id, BMDataDTO dto, HttpServletResponse httpServletResponse) {
        BMData bmData = BMDataService.saveCalc(id, dto);
        bmData.setBaseBM(BMDataService.calculateBaseBM(bmData));
        bmData.setTotalBM(BMDataService.calculateFinalBM(bmData, dto.getDietType(), dto.getNumDaysEx()));
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
