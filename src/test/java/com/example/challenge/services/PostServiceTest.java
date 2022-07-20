package com.example.challenge.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import com.example.challenge.dto.PostResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import com.example.challenge.models.Post;
import com.example.challenge.repositories.PostRepository;

@SpringBootTest
class PostServiceTest {

    @MockBean
    private PostRepository postRepository;

    @SpyBean
    private PostService postService;

    private Post postTest;

    @BeforeEach
    void setUp() {
        this.postTest = new Post();
        postTest.setBody("Body");
        postTest.setId(1L);
        postTest.setTitle("Title");
        postTest.setUserId(1L);

        Mockito.when(postRepository.findByTitleContaining(Mockito.anyString())).thenReturn(List.of(postTest));

        Mockito.when(postRepository.findById(1L)).thenReturn(Optional.of(postTest));

        Mockito.when(postRepository.findById(2L)).thenReturn(Optional.empty());

        Mockito.when(postRepository.findAll(PageRequest.of(0, 20))).thenReturn(new PageImpl<>(List.of(postTest)));
    }

    @Test
    void getPostsPage() {
        Pageable pageable = PageRequest.of(0, 20);
        Page<Post> page = this.postService.getPostsByPage(pageable);
        Mockito.verify(this.postRepository, Mockito.times(1)).findAll(pageable);
        assertEquals(1, page.getSize());
        assertEquals(this.postTest, page.getContent().get(0));
        assertEquals(1, page.getTotalPages());
    }

    @Test
    void getPostsByTitle() {
        String title = "Some title";
        List<PostResponseDto> posts = this.postService.getPostsByTitle(title);
        Mockito.verify(this.postRepository, Mockito.times(1)).findByTitleContaining(title);
        assertEquals(this.postTest.getId(), posts.get(0).getId());
        assertEquals(this.postTest.getTitle(), posts.get(0).getTitle());
        assertEquals(this.postTest.getBody(), posts.get(0).getBody());
        assertEquals(this.postTest.getUserId(), posts.get(0).getUserId());
    }

    @Test
    void getById() {
        PostResponseDto post = this.postService.getById(1L);
        Mockito.verify(this.postRepository, Mockito.times(1)).findById(1L);
        assertEquals(this.postTest.getId(), post.getId());
        assertEquals(this.postTest.getTitle(), post.getTitle());
        assertEquals(this.postTest.getBody(), post.getBody());
        assertEquals(this.postTest.getUserId(), post.getUserId());
    }

    @Test
    void getByIdNotExist() {
        assertThrows(ResourceNotFoundException.class, () -> this.postService.getById(2L));
        Mockito.verify(this.postRepository, Mockito.times(1)).findById(2L);
    }

    @Test
    void saveAll() {
        List<Post> list = List.of(this.postTest);
        this.postService.saveAll(list);
        Mockito.verify(this.postRepository, Mockito.times(1)).saveAll(list);
    }
}