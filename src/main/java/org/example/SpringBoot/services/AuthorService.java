package org.example.SpringBoot.services;

import org.example.SpringBoot.Models.Author;
import org.example.SpringBoot.dtos.AuthorDTO;

import java.util.List;

public interface AuthorService {

    List<AuthorDTO> readAll();

    AuthorDTO read(Long id);

    List<AuthorDTO> read(String email);

    List<AuthorDTO> read(String firstname, String lastname);

    AuthorDTO create(Author author);

    AuthorDTO update(Long id, Author author);

    void delete(Long id);

}
