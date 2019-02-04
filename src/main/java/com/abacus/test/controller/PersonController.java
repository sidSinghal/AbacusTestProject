package com.abacus.test.controller;

import com.abacus.test.exception.PersonException;
import com.abacus.test.pojo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.abacus.test.pojo.Person;
import com.abacus.test.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getAllPersons() {

        logger.info("Getting list of all persons");
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPersonById(@PathVariable String id) throws PersonException {

        Person person = personService.getPerson(id);
        logger.info("getting person with id: "+id+" --- "+person);

        if(person == null || person.getId().isEmpty()) {
            logger.error("Could not find person");
                throw new PersonException("Person does not exist");
        }

        return new ResponseEntity<>(personService.getPerson(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {

        logger.info("Adding a new person to the database");
        return new ResponseEntity<>(personService.addPerson(person), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Person> updatePerson(@PathVariable  String id, @RequestBody Person person) {

        logger.info("Updating person with id: "+id);
        logger.info("response ---: "+HttpStatus.OK);

        return new ResponseEntity<>(personService.updatePerson(id, person), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> deletePerson(@PathVariable String id) throws PersonException {

        Person person = personService.getPerson(id);

        if(person == null || person.getId().isEmpty()){

            logger.error("Could not find person with id: "+id);
            throw new PersonException("Person to remove does not exist");
        }

        logger.info("Deleting person with id: "+id);
        personService.deletePerson(id);

        return new ResponseEntity<>(new Response(HttpStatus.OK.value(), "Person has been deleted"), HttpStatus.OK);
    }
}
