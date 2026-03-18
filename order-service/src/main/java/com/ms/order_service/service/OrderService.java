package com.ms.order_service.service;

import com.ms.order_service.client.UserServiceClient;
import com.ms.order_service.client.UserServiceClientFeign;
import com.ms.order_service.dto.OrderRequestDTO;
import com.ms.order_service.dto.OrderResponseDTO;
import com.ms.order_service.dto.UserResponseDTO;
import com.ms.order_service.entiry.Order;
import com.ms.order_service.exception.UserNotFoundException;
import com.ms.order_service.mapper.OrderMapper;
import com.ms.order_service.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    //private final UserServiceClient userServiceClient;
    private final UserServiceClientFeign userServiceClientFeign;

    @Retry(name = "userServiceRetry")
    @CircuitBreaker(name = "userService", fallbackMethod = "userFallback")
    public OrderResponseDTO createOrder(OrderRequestDTO request) {

        log.info("Calling User Service for user {}", request.getUserId());

        UserResponseDTO user = userServiceClientFeign.getUserbyId(request.getUserId());

        Order order = orderMapper.toEntity(request);
        Order savedOrder = orderRepository.save(order);

        return orderMapper.toDTO(savedOrder);
    }

    public OrderResponseDTO userFallback(OrderRequestDTO request, Exception ex) {

        log.error("User Service is down, executing fallback", ex);

        Order order = orderMapper.toEntity(request);
        Order savedOrder = orderRepository.save(order);

        return OrderResponseDTO.builder()
                .orderId(savedOrder.getOrderId())
                .userId(request.getUserId())
                .productName(request.getProductName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();
    }
}
