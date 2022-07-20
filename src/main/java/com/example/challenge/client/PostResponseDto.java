package com.example.challenge.client;

import lombok.Data;

@Data
public class PostResponseDto {
    private Long id;
    private Long userId;
    private String title;
    private String body;
}
