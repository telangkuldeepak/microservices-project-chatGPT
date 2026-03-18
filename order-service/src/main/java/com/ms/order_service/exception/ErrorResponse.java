package com.ms.order_service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ErrorResponses {
    private int status;
    private String message;
    private String timestamp;
}
