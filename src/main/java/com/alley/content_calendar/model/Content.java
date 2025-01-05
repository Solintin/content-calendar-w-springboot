package com.alley.content_calendar.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Content (
        @NotEmpty @Positive
        Integer id,
        @NotEmpty
        String title,
        String desc,
        Status status,
        Type contentType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String url
){

}
