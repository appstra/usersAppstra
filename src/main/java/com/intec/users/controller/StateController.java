package com.intec.users.controller;

import com.intec.users.entity.State;
import com.intec.users.service.StateService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/state")
public class StateController {
    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @PostMapping("/savestate")
    @Operation(summary = "Guardar estado", description = "Crea estados")
    public ResponseEntity<State> saveState (@Validated @RequestBody State state){
        return ResponseEntity.ok(stateService.saveState(state));
    }
    @PutMapping("/updatestate")
    @Operation(summary = "actualiza estados", description = "actualiza estados")
    public ResponseEntity<State> updateState (@Validated @RequestBody State state){
        return ResponseEntity.ok(stateService.upDateState(state));
    }
    @DeleteMapping("/deletestate/{stateId}")
    @Operation(summary = "Elimina estados", description = "Elimina estados")
    public ResponseEntity<Boolean> deleteState (@PathVariable("stateId") Integer stateId){
        return ResponseEntity.ok(stateService.deleteState(stateId));
    }
    @GetMapping("/liststate")
    @Operation(summary = "Lista estados", description = "Lista estados")
    public ResponseEntity<List<State>> listState (){
        return ResponseEntity.ok(stateService.listState());
    }

}
