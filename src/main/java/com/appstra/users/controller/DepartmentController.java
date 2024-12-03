package com.appstra.users.controller;

import com.appstra.users.entity.Department;
import com.appstra.users.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/listdepartment/{departmentId}")
    @Operation(summary = "Lista departamentos", description = "Lista departamentos")
    public ResponseEntity<List<Department>> listDepartment (@PathVariable(name = "departmentId") Integer departmentId){
        return ResponseEntity.ok(departmentService.listDepartment(departmentId));
    }
}
