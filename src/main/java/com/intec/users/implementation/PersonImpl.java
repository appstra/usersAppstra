package com.intec.users.implementation;

import com.intec.users.entity.Person;
import com.intec.users.entity.User;
import com.intec.users.repository.PersonRepository;
import com.intec.users.repository.UserRepository;
import com.intec.users.service.PersonService;
import com.intec.users.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonImpl implements PersonService {

    private final PersonRepository personRepository;
    private final UserService userService;

    public PersonImpl(PersonRepository personRepository, UserRepository userRepository, UserService userRepository1, UserService userService) {
        this.personRepository = personRepository;
        this.userService = userService;
    }

    @Override
    public Person savePerson(Person person) {
        User savedUser = userService.saveUser(person.getUser());
        if (savedUser == null) {
            throw new IllegalStateException("Error al guardar el usuario: el objeto devuelto es nulo.");
        }
        person.getUser().setUserId(savedUser.getUserId());
        return personRepository.save(person);
    }

    @Override
    public Person upDatePerson(Person person) {
        Person exisPerson = personRepository.findById(person.getPersonId()).orElseThrow(() -> new IllegalArgumentException("la persona no existe: " + person.getPersonId()));
        person.setPersonCreationDate(exisPerson.getPersonCreationDate());
        person.setUser(exisPerson.getUser());

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
}
