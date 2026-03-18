package com.ms.order_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDTO {

    @NotNull
    private Long userId;
    @NotBlank
    private String productName;
    @Min(1)
    private int quantity;
    private double price;

}
