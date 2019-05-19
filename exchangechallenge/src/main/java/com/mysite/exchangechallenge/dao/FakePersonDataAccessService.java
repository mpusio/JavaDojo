package com.mysite.exchangechallenge.dao;

import com.mysite.exchangechallenge.module.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    public List<Person> selectAllPerson(){
        return DB;
    }

    @Override
    public int deletePerson(UUID id) {
        Optional<Person> maybePerson = selectPerson(id);
        if (maybePerson.isEmpty()) {
            return 0;
        }
        DB.remove(maybePerson.get());
        return 1;

    }

    @Override
    public int updatePerson(UUID id,  Person person) {
        return selectPerson(id)
                .map(p ->{
                    int index = DB.indexOf(p);
                    if (index >= 0) {
                        DB.set(index, new Person(id, person.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);

    }

    @Override
    public Optional<Person> selectPerson(UUID id) {
        return DB.stream().filter(person -> person.getId() == id).findFirst();
    }



}
