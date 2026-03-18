package com.ms.user_service.controller;

import com.ms.user_service.dto.UserRequest;
import com.ms.user_service.dto.UserResponse;
import com.ms.user_service.entity.User;
import com.ms.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest){
        log.info("UserController: createUser called with name: {}, email: {}", userRequest.getName(), userRequest.getEmail());

        UserResponse userResponse = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        log.info("UserController: getAllUsers called");
        List<UserResponse> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        log.info("UserController: getUserById called with id: {}", id);
        UserResponse userById = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userById); // ResponseEntity.ok(userById);
    }
}

