package com.ms.order_service.client;

import com.ms.order_service.dto.UserResponseDTO;
import com.ms.order_service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

// @Component
@RequiredArgsConstructor
@Slf4j
public class UserServiceClient {

    private final RestTemplate restTemplate;

    public UserResponseDTO getUserbyId(Long userId){
        log.info("Calling User Service for user {}", userId);

        String url = "http://USER-SERVICE/users/" + userId;

        try {

            log.info("Calling User Service for user {}", userId);

            return restTemplate.getForObject(url, UserResponseDTO.class);

        } catch (HttpClientErrorException.NotFound ex) {

            throw new UserNotFoundException(
                    "User not found with id: " + userId
            );
        }
    }

}
