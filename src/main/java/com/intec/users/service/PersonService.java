package com.intec.users.service;

import com.intec.users.entity.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonService {
    @Transactional
    Person savePerson(Person person);
    Person upDatePerson (Person person);
    Boolean deletePerson (Integer personId);
    List<Person> listPerson();
}
