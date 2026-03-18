package com.ms.user_service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
class ErrorResponseObject {

    private int status;
    private String message;
    private LocalDateTime timestamp;
}
