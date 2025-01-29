package com.appstra.users.controller;

import com.appstra.users.entity.City;
import com.appstra.users.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/listcity/{provinceId}")
    @Operation(summary = "Lista ciudades", description = "Lista ciudades")
    public ResponseEntity<List<City>> listCity (@PathVariable(name = "provinceId") Integer provinceId){
        return ResponseEntity.ok(cityService.findByProvinceProvinceId(provinceId));
    }
}
