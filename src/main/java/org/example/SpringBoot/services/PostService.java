package org.example.SpringBoot.services;

import org.example.SpringBoot.Models.Post;
import org.example.SpringBoot.dtos.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> readAll();

    PostDTO read(Long id);

    PostDTO create(Post post);

    PostDTO update(Long id, Post post);

    void delete(Long id);

}
