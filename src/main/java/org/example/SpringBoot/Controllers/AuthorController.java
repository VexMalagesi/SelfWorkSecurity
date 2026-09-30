package org.example.SpringBoot.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.SpringBoot.Models.Author;
import org.example.SpringBoot.Models.Post;
import org.example.SpringBoot.Repositories.AuthorRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorRepositories authorRepository;

    @GetMapping
    public  List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }
    @GetMapping("{id}")
    public  Author getAuthor(@PathVariable ("id") Long id ){
        return authorRepository.findById(id).get();
    }
@PostMapping
    public Author createAuthor(@RequestBody Author author ){
        return authorRepository.save(author);
}

 @PutMapping("{id}")
    public Author updateAuthor(@PathVariable("id") Long id,@RequestBody Author author){
        author.setId(id);
        return authorRepository.save(author);
 }

@DeleteMapping("{id}")
    public void deleteAuthor(@PathVariable("id") Long id){
        if(authorRepository.existsById(id)){
            Author author = authorRepository.findById(id).get();
            List<Post> authorPosts = author.getPosts();
            for (Post post : authorPosts) {
                post.setAuthor(null);
            }
            authorRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");
        }
    }


}
