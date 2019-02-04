package com.abacus.test.service;

import com.abacus.test.pojo.Person;
import com.abacus.test.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {

        List<Person> personList = new ArrayList<>();
        personList.addAll(personRepository.findAll());

        return personList;
    }

    public Person addPerson(Person person) {

        return personRepository.save(person);
    }

    public Person getPerson(String id) {

        return personRepository.findById(id).orElse(null);
    }

    public Person updatePerson(String id, Person person) {

        return personRepository.save(person);
    }

    public void deletePerson(String id) {

        personRepository.deleteById(id);
    }
}
