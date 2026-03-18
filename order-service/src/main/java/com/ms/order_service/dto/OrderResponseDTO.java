package com.ms.order_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponseDTO {
    private Long orderId;
    private Long userId;
    private String productName;
    private int quantity;
    private double price;
}
