package edu.br.unoesc.app.repositories;

import edu.br.unoesc.app.entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

    public Person findById(Long id);

    public Person findByName(String nome);

    public List<Person> findAll();
}
