package com.example.challenge.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PostApiClient {
    private WebClient webClient;

    private WebClient getWebClient() {
        return WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();
    }

    public PostResponseDto[] getPosts() {
        webClient = getWebClient();
        return webClient
                .get()
                .uri("/posts")
                .retrieve()
                .bodyToMono(PostResponseDto[].class).block();
    }

    public CommentResponseDto[] getComments(Long id) {
        webClient = getWebClient();
        return webClient
                .get()
                .uri(String.format("/posts/%s/comments", id))
                .retrieve()
                .bodyToMono(CommentResponseDto[].class).block();
    }
}