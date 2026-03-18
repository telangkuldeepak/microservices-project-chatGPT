package com.ms.order_service.client;

import com.ms.order_service.dto.UserResponseDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceClientFeign {

    @GetMapping("/users/{userId}")
    UserResponseDTO getUserbyId(@PathVariable Long userId);

}
