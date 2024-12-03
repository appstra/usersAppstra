package com.appstra.users.controller;

import com.appstra.users.entity.Province;
import com.appstra.users.service.ProvinceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/province")
public class ProvinceController {
    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }
    @GetMapping("/listprovince/{provinceId}")
    @Operation(summary = "Lista provincias", description = "Lista provincias")
    public ResponseEntity<List<Province>> listProvince (@PathVariable(name = "provinceId") Integer provinceId){
        return ResponseEntity.ok(provinceService.listDepartment(provinceId));
    }
}
