package org.example.SpringBoot.Repositories;

import org.example.SpringBoot.Models.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface AuthorRepositories extends ListCrudRepository<Author, Long> {


    List<Author> findByName(String firstname);


    List<Author> findBySurname(String surname);


    List<Author> findByNameAndSurname(String firstname, String surname);
}
