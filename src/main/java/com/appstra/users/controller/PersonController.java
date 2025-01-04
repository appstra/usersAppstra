package com.appstra.users.controller;

import com.appstra.users.entity.Person;
import com.appstra.users.service.PersonService;
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
    @GetMapping("/{personId}")
    @Operation(summary = "Informa persona", description = "Informa persona")
    public ResponseEntity<Person> getPerson (@PathVariable("personId") Integer personId){
        return ResponseEntity.ok(personService.getPerson(personId));
    }
    @GetMapping("PersonNumberIdentification/{personNumberIdentification}")
    @Operation(summary = "Informa persona por documento", description = "Informa persona por documento")
    public ResponseEntity<Person> getPersonPersonNumberIdentification (@PathVariable("personNumberIdentification") Integer personNumberIdentification){
        return ResponseEntity.ok(personService.getPersonPersonNumberIdentification(personNumberIdentification));
    }
}
