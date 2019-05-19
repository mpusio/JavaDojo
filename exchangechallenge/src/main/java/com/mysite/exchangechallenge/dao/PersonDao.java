package com.mysite.exchangechallenge.dao;

import com.mysite.exchangechallenge.module.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    int insertPerson (UUID id, Person person);

    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> selectAllPerson();

    int deletePerson(UUID id);

    int updatePerson(UUID id,  Person person);

    Optional<Person> selectPerson(UUID id);

}
