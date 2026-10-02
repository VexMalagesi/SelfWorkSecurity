package org.example.SpringBoot;

import org.example.SpringBoot.Models.Author;
import org.example.SpringBoot.Repositories.AuthorRepositories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;



@DataJpaTest


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ApplicationTests {

    @Autowired
    AuthorRepositories authorRepositories;

    @BeforeEach
    void load(){
        Author a1 = new Author();
        a1.setName("Giuseppe");
        a1.setSurname("Verdi");
        a1.setEmail("VerdiG@test.it");

        authorRepositories.save(a1);
    }


    @Test
    void contextLoads() {
    }

    @Test
    void findByName(){


        assertThat(authorRepositories.findByName("Giuseppe"))

                .extracting("name")

                .containsOnly("Giuseppe");
    }
}
