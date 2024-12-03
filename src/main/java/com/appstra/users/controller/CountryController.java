package com.appstra.users.controller;

import com.appstra.users.entity.Country;
import com.appstra.users.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/listcountry")
    @Operation(summary = "Lista paices", description = "Lista paices")
    public ResponseEntity<List<Country>> listCountry (){
        return ResponseEntity.ok(countryService.listCountry());
    }
}
