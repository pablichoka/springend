package com.kCalControl.controller;

import com.kCalControl.dto.BMDataDTO;
import com.kCalControl.dto.UpdatePersonalDataDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public interface BMDataController {

    @GetMapping("/auth/views/calcBM")
    String bmCalculator(Model model);
    @PostMapping("/auth/api/updateBMCalc/{id}")
    void updateBMCalc(@PathVariable("id")ObjectId id, BMDataDTO dto, HttpServletResponse httpServletResponse);
    @PostMapping("/auth/api/updateBMData/{id}")
    void updateBMData(@PathVariable("id")ObjectId id, UpdatePersonalDataDTO dto, HttpServletResponse httpServletResponse);

}
