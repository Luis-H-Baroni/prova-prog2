package edu.br.unoesc.app.controllers;

import edu.br.unoesc.app.dtos.PersonDTO;

import edu.br.unoesc.app.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/")
    public ResponseEntity getAllPersons() {
        List<PersonDTO> personList = personService.getAllPersons();
        return ResponseEntity.ok(personList);
    }

    @GetMapping("/{personId}")
    public ResponseEntity getPersonById(@PathVariable Long personId) {
        try {
            PersonDTO personDTO = personService.getPersonById(personId);
            return ResponseEntity.ok(personDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity createPerson(@RequestBody PersonDTO newPersonDTO) {
        try {
            newPersonDTO = personService.createPerson(newPersonDTO);
            return ResponseEntity.ok(newPersonDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/")
    public ResponseEntity updatePerson(@RequestBody PersonDTO updatedPersonDTO) {
        try {
            updatedPersonDTO = personService.updatePerson(updatedPersonDTO);
            return ResponseEntity.ok(updatedPersonDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity deletePersonById(@PathVariable Long personId) {
        try {
            personService.deletePersonById(personId);
            return ResponseEntity.ok("Person deleted!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
