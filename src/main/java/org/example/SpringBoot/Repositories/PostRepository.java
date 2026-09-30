package org.example.SpringBoot.Repositories;

import org.example.SpringBoot.Models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;


public interface PostRepository extends ListCrudRepository<Post, Long> {

}
