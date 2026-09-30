package org.example.SpringBoot.Controllers;

import org.example.SpringBoot.Models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.SpringBoot.Repositories.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @GetMapping("{id}")
    public Comment getComment(@PathVariable("id") Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @PutMapping("{id}")
    public Comment updateComment(@PathVariable("id") Long id, @RequestBody Comment comment) {
        comment.setId(id);
        return commentRepository.save(comment);
    }

    @DeleteMapping("{id}")
    public void deleteComment(@PathVariable("id") Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        }
    }
}