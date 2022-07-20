package com.example.challenge.controllers;

import com.example.challenge.services.CommentService;
import com.example.challenge.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @GetMapping("")
    @Operation(summary = "Get all posts")
    public ResponseEntity<?> getPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPosts());
    }

    @GetMapping("/paged")
    public ResponseEntity<?> getPosts(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsByPage(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getById(id));
    }

    @GetMapping("/title")
    public ResponseEntity<?> getByTitle(@RequestParam("title") String title) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsByTitle(title));
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<?> getComments(@PathVariable("postId") Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentsByPostId(postId));
    }
}
