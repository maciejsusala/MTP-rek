package com.MTP.rekrutacja.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
public record StarDto(
        Long id,

        @Getter
        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Distance cannot be blank")
        @Min(value = 0, message = "Distance must be greater than 0")
        @Getter
        long distance
) implements Serializable {

}