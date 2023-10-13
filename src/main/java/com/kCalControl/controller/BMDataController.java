package com.kCalControl.controller;

import com.kCalControl.dto.bmdata.RetrieveBMDataDTO;
import com.kCalControl.dto.bmdata.UpdateBMDataDTO;
import com.kCalControl.dto.bmdata.UpdatePersonalDataDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bm-data")
public interface BMDataController {
    @GetMapping("calc-bm-data/{id}")
    @ResponseBody
    ResponseEntity<RetrieveBMDataDTO> bmCalculator(@PathVariable ObjectId id);
    @PostMapping("save-bm-calc/{id}")
    ResponseEntity<String> updateBMCalc(@PathVariable ObjectId id, @RequestBody UpdateBMDataDTO dto);
    @PostMapping("save-bm-data/{id}")
    ResponseEntity<String> updateBMData(@PathVariable ObjectId id, @RequestBody UpdatePersonalDataDTO dto);

}
