package org.example.SpringBoot.Controllers;

import org.example.SpringBoot.Models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.SpringBoot.Repositories.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

     @Autowired
     PostRepository postRepository;

     @GetMapping
     public List<Post> getAllPosts() {
          return postRepository.findAll();
     }

     @GetMapping("{id}")
     public Post getPost(@PathVariable("id") Long id) {
          return postRepository.findById(id)
                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
     }

     @PostMapping
     public Post createPost(@RequestBody Post post) {
          return postRepository.save(post);
     }

     @PutMapping("{id}")
     public Post updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
          post.setId(id);
          return postRepository.save(post);
     }

     @DeleteMapping("{id}")
     public void deletePost(@PathVariable("id") Long id) {
          if (postRepository.existsById(id)) {
               postRepository.deleteById(id);
          } else {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
          }
     }
}