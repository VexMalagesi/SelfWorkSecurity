package org.example.SpringBoot.Controllers;

import org.example.SpringBoot.Models.Author;
import org.example.SpringBoot.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
  import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PathVariable;
  import org.springframework.web.bind.annotation.PostMapping;
   import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    public String authorsView(Model viewModel) {
        viewModel.addAttribute("title", "Authors");
        viewModel.addAttribute("authors", authorService.readAll());
        return "authors";
    }

    @GetMapping("create")
    public String createAuthorView(Model viewModel) {
        viewModel.addAttribute("title", "Create Author");
        viewModel.addAttribute("author", new Author());
        return "createAuthors";
    }


    @PostMapping
    public String createAuthor(@ModelAttribute Author author) {
        authorService.create(author);
        return "redirect:/authors";
    }

    @GetMapping("{id}/edit")
    public String editAuthorView(@PathVariable("id") Long id, Model viewModel) {
        viewModel.addAttribute("title", "Edit Author");
        viewModel.addAttribute("author", authorService.read(id));
        return "editAuthors";
    }

    @PostMapping("{id}")
    public String updateAuthor(@PathVariable("id") Long id, @ModelAttribute Author author) {
        authorService.update(id, author);
        return "redirect:/authors";
    }

    @PostMapping("{id}/delete")
    public String deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
        return "redirect:/authors";
    }

}
