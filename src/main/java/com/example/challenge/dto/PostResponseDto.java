package com.example.challenge.dto;

import lombok.Data;

@Data
public class PostResponseDto {
    private Long id;
    private Long userId;
    private String title;
    private String body;
}
