package com.example.api;

import com.example.domain.entity.Person;
import com.example.domain.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ExampleController {

    private final PersonRepository personRepository;

    @Autowired
    public ExampleController(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/person/{lastName}")
    public Person personInfo(@PathVariable final String lastName) {
        Optional<Person> foundPerson = personRepository.findByLastName(lastName);
        return foundPerson.orElse(new Person("",""));
    }

}
