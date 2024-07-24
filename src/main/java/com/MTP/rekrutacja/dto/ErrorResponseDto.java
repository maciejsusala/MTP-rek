package com.MTP.rekrutacja.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponseDto(
        String message,
        String details,
        LocalDateTime timestamp,
        List<String> validationErrors
) {
}