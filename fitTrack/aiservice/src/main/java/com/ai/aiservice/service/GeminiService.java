package com.ai.aiservice.service;


import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.MappingData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class GeminiService {

public  final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiUrl;
     @Value("${gemini.api.key}")
     private String geminiKey;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }
    public String getAnswer(String question) {
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[]{
                                Map.of("text", question)
                        })
                }
        );

        String response = webClient.post()
                .uri(geminiUrl + geminiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;

     }

}

