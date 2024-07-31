package com.MTP.rekrutacja.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record StarDto(
        Long id,

        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Distance cannot be blank")
        @Min(value = 0, message = "Distance must be greater than 0")
        long distance
) implements Serializable {
}