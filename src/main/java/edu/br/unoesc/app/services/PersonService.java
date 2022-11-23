package edu.br.unoesc.app.services;

import edu.br.unoesc.app.dtos.PersonDTO;

import edu.br.unoesc.app.entities.Person;

import edu.br.unoesc.app.repositories.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static java.time.Period.*;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<PersonDTO> getAllPersons() {
        List<PersonDTO> personListDTO = new ArrayList<PersonDTO>();
        List<Person> persons = personRepository.findAll();
        persons.forEach(person -> {
            PersonDTO personDTO = new PersonDTO(person.getId(), person.getName(), person.getSurname(),
                    person.getCPF(), person.getRG());
            personListDTO.add(personDTO);
        });
        return personListDTO;
    }

    public PersonDTO getPersonById(Long personId) {
        Person person = personRepository.findById(personId);
        if (person == null)
            throw new RuntimeException("Person not found");
        PersonDTO personDTO = new PersonDTO(person.getId(), person.getName(), person.getSurname(),
                person.getCPF(), person.getRG());
        return personDTO;
    }

    public PersonDTO createPerson(PersonDTO personDTO) {

        Person personToBeCreated;

        if (personDTO.getId() != null) {
            Person existingPerson = personRepository.findById(personDTO.getId());
            if (existingPerson != null)
                throw new RuntimeException("This person already exists");

        }

        personToBeCreated = new Person();

        return this.registerPerson(personToBeCreated, personDTO);
    }

    public PersonDTO updatePerson(PersonDTO personDTO) {

        if (personDTO.getId() == null)
            throw new RuntimeException("Person not found");

        Person personToBeUpdated = personRepository.findById(personDTO.getId());

        if (personToBeUpdated == null) {
            throw new RuntimeException("Person not found");
        }

        boolean validChange = this.validatePersonUpdate(personToBeUpdated,
                5);

        if (validChange) {

            personToBeUpdated.setUpdatedAt(LocalDateTime.now());

            return this.registerPerson(personToBeUpdated, personDTO);

        } else
            throw new RuntimeException("This person cannot be changed");

    }

    public void deletePersonById(Long personId) {

        Person personToBeDeleted = personRepository.findById(personId);

        if (personToBeDeleted == null) {
            throw new RuntimeException("Person not found");
        }

        boolean validChange = this.validatePersonUpdate(personToBeDeleted,
                5);

        if (validChange) {

            personToBeDeleted.setUpdatedAt(LocalDateTime.now());

            this.personRepository.delete(personToBeDeleted);
        } else
            throw new RuntimeException("This person cannot be changed");

    }

    private PersonDTO registerPerson(Person personToBeCreated, PersonDTO personDTO) {

        personToBeCreated.setName(personDTO.getName());
        personToBeCreated.setSurname(personDTO.getSurname());
        personToBeCreated.setCPF(personDTO.getCPF());
        personToBeCreated.setRG(personDTO.getRG());

        personRepository.save(personToBeCreated);
        personDTO.setId(personToBeCreated.getId());

        return personDTO;
    }

    private boolean validatePersonUpdate(Person person, int daysInterval) {

        Period diff = between(person.getCreatedAt().toLocalDate(),
                LocalDate.now());
        return (diff.getDays() <= daysInterval) ? true : false;

    }

}
