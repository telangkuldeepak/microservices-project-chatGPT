package com.ms.order_service.mapper;

import com.ms.order_service.dto.OrderRequestDTO;
import com.ms.order_service.dto.OrderResponseDTO;
import com.ms.order_service.entiry.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toEntity(OrderRequestDTO orderRequestDTO) {
        return Order.builder()
                .userId(orderRequestDTO.getUserId())
                .productName(orderRequestDTO.getProductName())
                .quantity(orderRequestDTO.getQuantity())
                .price(orderRequestDTO.getPrice())
                .build();
    }
    public OrderResponseDTO toDTO(Order order) {
        return OrderResponseDTO.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .productName(order.getProductName())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .build();

    }
}
