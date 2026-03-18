package com.ms.user_service.service;

import com.ms.user_service.dto.UserRequest;
import com.ms.user_service.dto.UserResponse;
import com.ms.user_service.entity.User;
import com.ms.user_service.exception.UserNotFoundException;
import com.ms.user_service.mapper.UserMapper;
import com.ms.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse createUser(UserRequest userRequest){
        log.info("Creating user with email {}", userRequest.getEmail());
        User user = userMapper.toEntity(userRequest);
        User savedUser = userRepository.save(user);
        UserResponse userResponse = userMapper.toResponse(savedUser);
        return userResponse;
    }

    public List<UserResponse> getAllUsers(){
        log.info("Getting all users");
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse).
                collect(Collectors.toList());
    }


    public UserResponse getUserById(Long id) {
        log.debug("Searching for user with id: {}", id);

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        UserResponse userResponse = userMapper.toResponse(user);

        log.info("User found: id={}, name={}, email={}", user.getId(), user.getName(), user.getEmail());

        return userResponse;
    }
}
