package com.kCalControl.controller;

import com.kCalControl.dto.nutrients.RetrieveBasicNutrientsDTO;
import com.kCalControl.dto.nutrients.RetrieveFullNutrientsDTO;
import com.kCalControl.dto.nutrients.RetrieveMineralsDTO;
import com.kCalControl.dto.nutrients.RetrieveVitaminsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nutrients")
public interface NutrientController {

    @GetMapping("get-nutrients/{id}")
    @ResponseBody
    ResponseEntity<RetrieveBasicNutrientsDTO> getNutrients(@PathVariable Integer id);

    @GetMapping("get-vitamins/{id}")
    @ResponseBody
    ResponseEntity<RetrieveVitaminsDTO> getVitamins(@PathVariable Integer id);

    @GetMapping("get-minerals/{id}")
    @ResponseBody
    ResponseEntity<RetrieveMineralsDTO> getMinerals(@PathVariable Integer id);

}
