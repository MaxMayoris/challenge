package com.example.challenge.services;

import com.example.challenge.dto.CommentResponseDto;
import com.example.challenge.models.Comment;
import com.example.challenge.repositories.CommentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    public List<Comment> saveAll(List<Comment> comments) {
        return commentRepository.saveAll(comments);
    }
    @Transactional
    public List<CommentResponseDto> getCommentsByPostId(Long id) {
        return commentRepository.findCommentsByPostId(id).stream().map(entity -> {
            CommentResponseDto commentResponseDto = objectMapper.convertValue(entity, CommentResponseDto.class);
            commentResponseDto.setPostId(entity.getPost().getId());
            return commentResponseDto;
        }).collect(Collectors.toList());
    }
}
