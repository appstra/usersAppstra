package com.appstra.users.service;

import com.appstra.users.entity.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonService {
    @Transactional
    Person savePerson(Person person);
    Person upDatePerson (Person person);
    Boolean deletePerson (Integer personId);
    List<Person> listPerson();
    Person getPerson (Integer personId);
    Person getPersonPersonNumberIdentification(Integer personNumberIdentification);
}
