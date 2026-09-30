package org.example.SpringBoot.Repositories;

import org.example.SpringBoot.Models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

// Come AuthorRepositories: estendendo CrudRepository<Comment, Long> hai già
// gratis save/findById/findAll/deleteById per l'entità Comment.
// Qui dentro andranno le tue query derivate/native per Comment
// (stessa logica vista in AuthorRepositories: findBy<CampoEntità>...).
public interface CommentRepository extends ListCrudRepository<Comment, Long> {

}
