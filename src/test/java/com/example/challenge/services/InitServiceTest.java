package com.example.challenge.services;

import com.example.challenge.client.CommentResponseDto;
import com.example.challenge.client.PostResponseDto;
import com.example.challenge.models.Comment;
import com.example.challenge.models.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InitServiceTest {

    private PostResponseDto postTest;

    private CommentResponseDto commentTest;

    @BeforeEach
    void setUp() {
        this.postTest = new PostResponseDto();
        postTest.setBody("Body");
        postTest.setId(1L);
        postTest.setTitle("Title");
        postTest.setUserId(1L);

        this.commentTest = new CommentResponseDto();
        commentTest.setBody("Body");
        commentTest.setId(1L);
        commentTest.setEmail("Email");
        commentTest.setName("Name");
        commentTest.setPostId(1L);
    }

    @Test
    void saveDatabase() {

    }
}