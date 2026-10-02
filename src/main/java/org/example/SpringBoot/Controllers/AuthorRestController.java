package org.example.SpringBoot.Controllers;

import org.example.SpringBoot.dtos.AuthorDTO;
import org.example.SpringBoot.services.AuthorService;
  import org.springframework.web.bind.annotation.*;
 import org.springframework.beans.factory.annotation.Autowired;
import org.example.SpringBoot.Models.Author;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorService.readAll();
    }

    @GetMapping("{id}")
    public AuthorDTO getAuthor(@PathVariable("id") Long id) {
        return authorService.read(id);
    }

    @PostMapping
    public AuthorDTO createAuthor(@RequestBody Author author) {
        return authorService.create(author);
    }

    @PutMapping("{id}")
    public AuthorDTO updateAuthor(@PathVariable("id") Long id, @RequestBody Author author) {
        // author.setId(id);
        return authorService.update(id, author);
    }

    @DeleteMapping("{id}")
    public void deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
    }
}
