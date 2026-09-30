package org.example.SpringBoot.Repositories;

import org.example.SpringBoot.Models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

// Stessa idea per Post: CrudRepository<Post, Long> dà già i metodi base.
// Qui andranno le tue query derivate/native per Post.
public interface PostRepository extends ListCrudRepository<Post, Long> {

}
