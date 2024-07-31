package com.MTP.rekrutacja.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data transfer object for error responses.
 */
public record ErrorResponseDto(
        String message,
        String details,
        LocalDateTime timestamp,
        List<String> validationErrors
) {
}