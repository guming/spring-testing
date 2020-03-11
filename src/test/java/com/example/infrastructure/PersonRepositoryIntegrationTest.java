package com.example.infrastructure;

import com.example.domain.entity.Person;
import com.example.domain.repository.PersonRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryIntegrationTest {

    @Autowired
    private PersonRepository personRepository;

    @After
    public void tearDown() {
        personRepository.deleteAll();
    }

    @Test
    public void shouldSaveAndFetchPerson() {
        //given
        Person peter = new Person("Peter", "Pan");
        personRepository.save(peter);
        //when
        Optional<Person> maybePeter = personRepository.findByLastName("Pan");
        //then
        assertThat(maybePeter, is(Optional.of(peter)));
        assertThat(maybePeter.get().getLastName(), is(peter.getLastName()));
    }
}