package com.appstra.users.repository;

import com.appstra.users.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer> {
    Person findByPersonId (Integer personId);
    Person findByPersonNumberIdentification(Integer personNumberIdentification);
}
