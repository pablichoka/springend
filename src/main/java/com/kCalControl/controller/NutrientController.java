package com.kCalControl.controller;

import com.kCalControl.dto.nutrients.RetrieveBasicNutrientsDTO;
import com.kCalControl.dto.nutrients.RetrieveFullNutrientsDTO;
import com.kCalControl.dto.nutrients.RetrieveMineralsDTO;
import com.kCalControl.dto.nutrients.RetrieveVitaminsDTO;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nutrients")
public interface NutrientController {

    @GetMapping("get-basic-nutrients/{id}")
    @ResponseBody
    ResponseEntity<RetrieveBasicNutrientsDTO> getBasicNutrients(@PathVariable ObjectId id);

    @GetMapping("get-full-nutrients/{id}")
    @ResponseBody
    ResponseEntity<RetrieveFullNutrientsDTO> getFullNutrients(@PathVariable ObjectId id);

    @GetMapping("get-vitamins/{id}")
    @ResponseBody
    ResponseEntity<RetrieveVitaminsDTO> getVitamins(@PathVariable ObjectId id);

    @GetMapping("get-minerals/{id}")
    @ResponseBody
    ResponseEntity<RetrieveMineralsDTO> getMinerals(@PathVariable ObjectId id);

}