package com.example.challenge.services;

import com.example.challenge.dto.PostResponseDto;
import com.example.challenge.models.Post;
import com.example.challenge.repositories.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    public List<Post> saveAll(List<Post> posts) {
        return postRepository.saveAll(posts);
    }

    @Transactional
    public List<PostResponseDto> getPosts() {
        return postRepository.findAll().stream().map(entity -> objectMapper.convertValue(entity, PostResponseDto.class)).collect(Collectors.toList());
    }

    public Page<Post> getPostsByPage(Pageable page) {
        return postRepository.findAll(page);
    }

    @Transactional
    public List<PostResponseDto> getPostsByTitle(String title) {
        return postRepository.findByTitleContaining(title).stream().map(entity -> objectMapper.convertValue(entity, PostResponseDto.class)).collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto getById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post id not found"));
        return objectMapper.convertValue(post, PostResponseDto.class);
    }
}
