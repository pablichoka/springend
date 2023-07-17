package com.kCalControl.controller;

import com.kCalControl.dto.BMDataDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public interface BMDataController {

    @GetMapping("/views/calcBM")
    String bmCalculator(Model model);
    @PostMapping("/api/updateBMCalc/{id}")
    void updateBMCalc(@PathVariable("id")ObjectId id, BMDataDTO dto, HttpServletResponse httpServletResponse);
    @PostMapping("/api/updateBMData/{id}")
    void updateBMData(@PathVariable("id")ObjectId id, BMDataDTO dto, HttpServletResponse httpServletResponse);

}
