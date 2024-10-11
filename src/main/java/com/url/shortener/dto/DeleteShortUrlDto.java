package com.url.shortener.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DeleteShortUrlDto {

    @Size(min = 6, max = 6, message = "URL ID must be exactly 6 characters long.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "URL ID must be alphanumeric.")
    private String urlId;
}
