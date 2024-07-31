package com.MTP.rekrutacja.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record StarDto (
        Long id,

        @NotBlank(message = "Name cannot be blank")
        String name,

        //TODO add validation for distance
        long distance
) implements Serializable {
}