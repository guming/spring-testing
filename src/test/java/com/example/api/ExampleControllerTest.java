package com.example.api;

import com.example.domain.entity.Person;
import com.example.domain.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * spring webmvc test
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ExampleController.class)
public class ExampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;


    @Test
    public void shouldReturnHelloWorld() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(content().string("Hello World!"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReturnPerson() throws Exception {
        //given
        Person peter = new Person("Peter", "Pan");
        given(personRepository.findByLastName("Pan")).willReturn(Optional.of(peter));
        //when & then
        mockMvc.perform(get("/person/Pan"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Peter"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReturnWithEmptyPerson() throws Exception {
        //given
        Person peter = new Person("Peter", "Pan");
        given(personRepository.findByLastName("Pan")).willReturn(Optional.of(peter));
        //when & then
        mockMvc.perform(get("/person/Ming"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(""))
                .andExpect(status().is2xxSuccessful());
    }

}
