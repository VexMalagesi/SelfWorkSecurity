package org.example.SpringBoot.services;

import org.example.SpringBoot.Models.Post;
import org.example.SpringBoot.Repositories.PostRepository;
import org.example.SpringBoot.dtos.PostDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<PostDTO> readAll() {
        List<PostDTO> dtos = new ArrayList<PostDTO>();
        for (Post post : postRepository.findAll()) {
            dtos.add(mapper.map(post, PostDTO.class));
        }
        return dtos;
    }

    @Override
    public PostDTO read(Long id) {
        Optional<Post> optPost = postRepository.findById(id);
        if (optPost.isPresent()) {
            return mapper.map(optPost.get(), PostDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post id=" + id + " not found");
        }
    }

    @Override
    public PostDTO create(Post post) {
        Post saved = postRepository.save(post);
        return mapper.map(saved, PostDTO.class);
    }

    @Override
    public PostDTO update(Long id, Post post) {
        post.setId(id);
        Post saved = postRepository.save(post);
        return mapper.map(saved, PostDTO.class);
    }

    @Override
    public void delete(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post id=" + id + " not found");
        }
    }
}
