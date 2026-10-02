package org.example.SpringBoot.Controllers;

import org.example.SpringBoot.Models.Post;
import org.example.SpringBoot.Repositories.AuthorRepositories;
import org.example.SpringBoot.Repositories.PostRepository;
import org.example.SpringBoot.services.PostService;
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
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    AuthorRepositories authorRepository;

    @GetMapping
    public String postsView(Model viewModel) {
        viewModel.addAttribute("title", "Posts");
        viewModel.addAttribute("posts", postService.readAll());
        return "posts";
    }

         @GetMapping("create")
          public String createPostView(Model viewModel) {
           viewModel.addAttribute("title", "Create Post");
         viewModel.addAttribute("post", new Post());
           viewModel.addAttribute("authors", authorRepository.findAll());
        return "createPosts";
    }

    @PostMapping
    public String createPost(@ModelAttribute Post post, @RequestParam Long authorId) {
        authorRepository.findById(authorId).ifPresent(post::setAuthor);
        postService.create(post);
        return "redirect:/posts";
    }

    @GetMapping("{id}/edit")
    public String editPostView(@PathVariable("id") Long id, Model viewModel) {
        viewModel.addAttribute("title", "Edit Post");
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post id=" + id + " not found"));
        viewModel.addAttribute("post", post);
        viewModel.addAttribute("authors", authorRepository.findAll());
        return "editPost";
    }

    @PostMapping("{id}")
    public String updatePost(@PathVariable("id") Long id, @ModelAttribute Post post, @RequestParam Long authorId) {
        authorRepository.findById(authorId).ifPresent(post::setAuthor);
        postService.update(id, post);
        return "redirect:/posts";
    }

    @PostMapping("{id}/delete")
    public String deletePost(@PathVariable("id") Long id) {
        postService.delete(id);
        return "redirect:/posts";
    }

}
