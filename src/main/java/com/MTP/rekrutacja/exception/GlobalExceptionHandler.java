package com.MTP.rekrutacja.exception;

import com.MTP.rekrutacja.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation exceptions.
     *
     * @param ex the MethodArgumentNotValidException
     * @return the ErrorResponseDto containing validation error details
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        return new ErrorResponseDto(
                "Validation failed",
                "Check 'validationErrors' for details",
                LocalDateTime.now(),
                errors
        );
    }

    /**
     * Handles StarNotFoundException.
     *
     * @param ex the StarNotFoundException
     * @return the ErrorResponseDto containing error details
     */
    @ExceptionHandler(StarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleStarNotFoundException(StarNotFoundException ex) {
        return new ErrorResponseDto(
                "Star has not been found",
                ex.getMessage(),
                LocalDateTime.now(),
                null
        );
    }
}