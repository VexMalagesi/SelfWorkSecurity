package org.example.SpringBoot.Controllers;

import org.example.SpringBoot.Models.Comment;
import org.example.SpringBoot.Repositories.CommentRepository;
import org.example.SpringBoot.Repositories.PostRepository;
import org.example.SpringBoot.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
  @RequestMapping("/comments")
  public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentRepository commentRepository;

         @Autowired
    PostRepository postRepository;

      @GetMapping
    public String commentsView(Model viewModel) {
        viewModel.addAttribute("title", "Comments");
        viewModel.addAttribute("comments", commentService.readAll());
        return "comments";
    }

    @GetMapping("create")
    public String createCommentView(Model viewModel) {
        viewModel.addAttribute("title", "Create Comment");
        viewModel.addAttribute("comment", new Comment());
        viewModel.addAttribute("posts", postRepository.findAll());
        return "createComments";
    }

    @PostMapping
    public String createComment(@ModelAttribute Comment comment, @RequestParam Long postId) {
        postRepository.findById(postId).ifPresent(comment::setPost);
        commentService.create(comment);
        return "redirect:/comments";
    }

    @GetMapping("{id}/edit")
    public String editCommentView(@PathVariable("id") Long id, Model viewModel) {
        viewModel.addAttribute("title", "Edit Comment");
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment id=" + id + " not found"));
        viewModel.addAttribute("comment", comment);
        viewModel.addAttribute("posts", postRepository.findAll());
        return "editComment";
    }

    @PostMapping("{id}")
    public String updateComment(@PathVariable("id") Long id, @ModelAttribute Comment comment, @RequestParam Long postId) {
        postRepository.findById(postId).ifPresent(comment::setPost);
        commentService.update(id, comment);
        return "redirect:/comments";
    }

    @PostMapping("{id}/delete")
    public String deleteComment(@PathVariable("id") Long id) {
        commentService.delete(id);
        return "redirect:/comments";
    }

}
