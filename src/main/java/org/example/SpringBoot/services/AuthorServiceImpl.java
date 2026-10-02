package org.example.SpringBoot.services;

import org.example.SpringBoot.Models.Author;
import org.example.SpringBoot.Repositories.AuthorRepositories;
import org.example.SpringBoot.dtos.AuthorDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepositories authorRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<AuthorDTO> readAll() {
        List<AuthorDTO> dtos = new ArrayList<AuthorDTO>();
        for (Author author : authorRepository.findAll()) {
            dtos.add(mapper.map(author, AuthorDTO.class));
        }
        return dtos;
    }

    @Override
    public AuthorDTO read(Long id) {
        Optional<Author> optAuthor = authorRepository.findById(id);
        if (optAuthor.isPresent()) {
            return mapper.map(optAuthor.get(), AuthorDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author id=" + id + " not found");
        }
    }

    @Override
    public List<AuthorDTO> read(String email) {
        if (email == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        List<AuthorDTO> dtos = new ArrayList<AuthorDTO>();
        for (Author author : authorRepository.findByEmail(email)) {
            dtos.add(mapper.map(author, AuthorDTO.class));
        }
        return dtos;
    }

    @Override
    public List<AuthorDTO> read(String firstname, String lastname) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public AuthorDTO create(Author author) {
        Author saved = authorRepository.save(author);
        return mapper.map(saved, AuthorDTO.class);
    }

    @Override
    public AuthorDTO update(Long id, Author author) {
        author.setId(id);
        Author saved = authorRepository.save(author);
        return mapper.map(saved, AuthorDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author id=" + id + " not found");
        }
    }
}
