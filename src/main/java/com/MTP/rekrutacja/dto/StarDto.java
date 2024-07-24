package com.MTP.rekrutacja.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record StarDto (
        Long id,

        @NotBlank(message = "Name cannot be blank")
        String name,

        long distance
) implements Serializable {
}