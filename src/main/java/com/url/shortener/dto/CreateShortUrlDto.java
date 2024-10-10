package com.url.shortener.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class CreateShortUrlDto {

    private Long id;

    @NotEmpty(message = "originalUrl address can not be a null or empty")
    @Pattern(regexp = "^(https?:\\/\\/)?(www\\.)?[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,}(\\/.*)?$",
            message = "Invalid URL format. Must be a valid http or https URL.")
    private String originalUrl;
    @Size(min = 6, max = 6, message = "URL ID must be exactly 6 characters long.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "URL ID must be alphanumeric.")
    private String urlId;

}
