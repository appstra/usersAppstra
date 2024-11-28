package com.intec.users.controller;

import com.intec.users.entity.StateType;
import com.intec.users.service.StateTypeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/statetype")
public class StateTypeController {
    private final StateTypeService stateTypeService;

    public StateTypeController(StateTypeService stateTypeService) {
        this.stateTypeService = stateTypeService;
    }

    @PostMapping("/savestatetype")
    @Operation(summary = "Guardar Tipos de estados", description = "Crea Tipos de estados")
    public ResponseEntity<StateType> saveStateType (@Validated @RequestBody StateType stateType){
        return ResponseEntity.ok(stateTypeService.saveStateType(stateType));
    }
    @PutMapping("/updatestatetype")
    @Operation(summary = "actualiza Tipos de estados", description = "actualiza Tipos de estados")
    public ResponseEntity<StateType> updateStateType (@Validated @RequestBody StateType stateType){
        return ResponseEntity.ok(stateTypeService.upDateStateType(stateType));
    }
    @DeleteMapping("/updatestatetype/{stateTypeId}")
    @Operation(summary = "Elimina Tipos de estados", description = "Elimina Tipos de estados")
    public ResponseEntity<Boolean> deleteStateType (@PathVariable("stateTypeId") Integer stateTypeId){
        return ResponseEntity.ok(stateTypeService.deleteStateType(stateTypeId));
    }
    @GetMapping("/liststatetype")
    @Operation(summary = "Lista Tipos de estados", description = "Lista Tipos de estados")
    public ResponseEntity<List<StateType>> listStateType (){
        return ResponseEntity.ok(stateTypeService.listStateType());
    }

}
