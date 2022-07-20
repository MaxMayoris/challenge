package com.example.challenge.services;

import com.example.challenge.dto.CommentResponseDto;
import com.example.challenge.models.Comment;
import com.example.challenge.models.Post;
import com.example.challenge.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CommentServiceTest {

    @MockBean
    private CommentRepository commentRepository;

    @SpyBean
    private CommentService commentService;

    private Comment commentTest;

    @BeforeEach
    void setUp() {
        Post post = new Post();
        this.commentTest = new Comment();
        commentTest.setBody("Body");
        commentTest.setId(1L);
        commentTest.setEmail("Email");
        commentTest.setName("Name");
        commentTest.setPost(post);

        Mockito.when(commentRepository.findCommentsByPostId(Mockito.anyLong())).thenReturn(List.of(commentTest));
    }

    @Test
    void saveAll() {
        List<Comment> list = List.of(this.commentTest);
        this.commentService.saveAll(list);
        Mockito.verify(this.commentRepository, Mockito.times(1)).saveAll(list);
    }

    @Test
    void getCommentsByPostId() {
        List<CommentResponseDto> commentResponseDto = this.commentService.getCommentsByPostId(1L);
        Mockito.verify(this.commentRepository, Mockito.times(1)).findCommentsByPostId(1L);
        assertEquals(this.commentTest.getId(), commentResponseDto.get(0).getId());
        assertEquals(this.commentTest.getBody(), commentResponseDto.get(0).getBody());
        assertEquals(this.commentTest.getEmail(), commentResponseDto.get(0).getEmail());
        assertEquals(this.commentTest.getName(), commentResponseDto.get(0).getName());
        assertEquals(this.commentTest.getPost().getId(), commentResponseDto.get(0).getPostId());
    }
}