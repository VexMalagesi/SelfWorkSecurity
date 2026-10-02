package org.example.SpringBoot.services;

import org.example.SpringBoot.Models.Comment;
import org.example.SpringBoot.Repositories.CommentRepository;
import org.example.SpringBoot.dtos.CommentDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CommentDTO> readAll() {
        List<CommentDTO> dtos = new ArrayList<CommentDTO>();
        for (Comment comment : commentRepository.findAll()) {
            dtos.add(mapper.map(comment, CommentDTO.class));
        }
        return dtos;
    }

    @Override
    public CommentDTO read(Long id) {
        Optional<Comment> optComment = commentRepository.findById(id);
        if (optComment.isPresent()) {
            return mapper.map(optComment.get(), CommentDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment id=" + id + " not found");
        }
    }

    @Override
    public CommentDTO create(Comment comment) {
        Comment saved = commentRepository.save(comment);
        return mapper.map(saved, CommentDTO.class);
    }

    @Override
    public CommentDTO update(Long id, Comment comment) {
        comment.setId(id);
        Comment saved = commentRepository.save(comment);
        return mapper.map(saved, CommentDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment id=" + id + " not found");
        }
    }
}
