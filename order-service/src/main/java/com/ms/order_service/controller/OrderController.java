package com.ms.order_service.controller;

import com.ms.order_service.dto.OrderRequestDTO;
import com.ms.order_service.dto.OrderResponseDTO;
import com.ms.order_service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO) {
        log.info("Creating order for user {}", orderRequestDTO.getUserId());

        return ResponseEntity.ok( orderService.createOrder(orderRequestDTO));
    }
}
