package com.example.challenge.services;

import com.example.challenge.client.CommentResponseDto;
import com.example.challenge.client.PostApiClient;
import com.example.challenge.client.PostResponseDto;
import com.example.challenge.models.Comment;
import com.example.challenge.models.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InitService {
    @Autowired
    private PostApiClient postApiClient;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void saveDatabase() {
        List<Post> posts = savePosts();
        saveComments(posts);
    }

    private List<Post> savePosts() {
        PostResponseDto[] posts = postApiClient.getPosts();
        List<Post> postsEntity = Arrays.stream(posts).map(dto -> objectMapper.convertValue(dto, Post.class)).collect(Collectors.toList());
        return postService.saveAll(postsEntity);
    }

    private void saveComments(List<Post> posts) {
        posts.stream().forEach(post -> {
            CommentResponseDto[] comments = postApiClient.getComments(post.getId());
            List<Comment> commentsEntity = Arrays.stream(comments).map(dto -> {
                Comment commentEntity = objectMapper.convertValue(dto, Comment.class);
                commentEntity.setPost(post);
                return commentEntity;
            }).collect(Collectors.toList());
            commentService.saveAll(commentsEntity);
        });
    }
}