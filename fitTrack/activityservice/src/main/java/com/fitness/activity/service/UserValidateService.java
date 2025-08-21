package com.fitness.activity.service;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
public class UserValidateService {

    private final WebClient userServiceWebClient;

    public Boolean validateUser(String userId){
        try{
            return userServiceWebClient.get()
                    .uri("api/users/{userId}/validate",userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

        }catch (WebClientResponseException e) {

            if(e.getStatusCode()== HttpStatus.NOT_FOUND){
                throw new RuntimeException("NOT FOUND ID" + userId);
            }

        }
return false;
    }
}
