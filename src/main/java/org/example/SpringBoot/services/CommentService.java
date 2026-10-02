package org.example.SpringBoot.services;

import org.example.SpringBoot.Models.Comment;
import org.example.SpringBoot.dtos.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> readAll();

    CommentDTO read(Long id);

    CommentDTO create(Comment comment);

    CommentDTO update(Long id, Comment comment);

    void delete(Long id);

}
