package org.example.SpringBoot.Repositories;

import org.example.SpringBoot.Models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

public interface CommentRepository extends ListCrudRepository<Comment, Long> {

}
