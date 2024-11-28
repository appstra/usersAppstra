package com.intec.users.controller;

import com.intec.users.entity.Person;
import com.intec.users.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/saveperson")
    @Operation(summary = "Guardar persona", description = "Guardar persona")
    public ResponseEntity<Person> savePerson(@Validated @RequestBody Person person){
        return ResponseEntity.ok(personService.savePerson(person));
    }
    @PutMapping("/updatestate")
    @Operation(summary = "actualiza personas", description = "actualiza persona")
    public ResponseEntity<Person> updatePerson (@Validated @RequestBody Person person){
        return ResponseEntity.ok(personService.upDatePerson(person));
    }
    @DeleteMapping("/deletestate/{personId}")
    @Operation(summary = "Elimina personas", description = "Elimina personas")
    public ResponseEntity<Boolean> deletePerson (@PathVariable("stateId") Integer personId){
        return ResponseEntity.ok(personService.deletePerson(personId));
    }
    @GetMapping("/liststate")
    @Operation(summary = "Lista personas", description = "Lista personas")
    public ResponseEntity<List<Person>> listPerson (){
        return ResponseEntity.ok(personService.listPerson());
    }
}
