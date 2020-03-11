package com.example.domain.repository;

import com.example.domain.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

    Optional<Person> findByLastName(String lastName);

}
