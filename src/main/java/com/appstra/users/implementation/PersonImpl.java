package com.appstra.users.implementation;

import com.appstra.users.entity.Person;
import com.appstra.users.service.PersonService;
import com.appstra.users.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person savePerson(Person person) {
        person.setPersonCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        person.setPersonEditDate(Timestamp.valueOf(LocalDateTime.now()));
        return personRepository.save(person);
    }

    @Override
    public Person upDatePerson(Person person) {
        Person exisPerson = personRepository.findById(person.getPersonId()).orElseThrow(() -> new IllegalArgumentException("la persona no existe: " + person.getPersonId()));
        person.setPersonCreationDate(exisPerson.getPersonCreationDate());
        person.setPersonEditDate(Timestamp.valueOf(LocalDateTime.now()));
        if(exisPerson.getUser() != null && exisPerson.getUser().getUserId() != null){
            person.setUser(exisPerson.getUser());
        }

        return personRepository.save(person);
    }

    @Override
    public Boolean deletePerson(Integer personId) {
        if (personRepository.existsById(personId)) {
            personRepository.deleteById(personId);
            return true;
        }
        return false;
    }

    @Override
    public List<Person> listPerson() {
        return personRepository.findAll();
    }

    @Override
    public Person getPerson(Integer personId) {
        return personRepository.findByPersonId(personId);
    }

    @Override
    public Person getPersonPersonNumberIdentification(Integer personNumberIdentification) {
        return personRepository.findByPersonNumberIdentification(personNumberIdentification);
    }
}
