package com.abacus.test.repository;

import com.abacus.test.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends JpaRepository<Person, String> {
}
