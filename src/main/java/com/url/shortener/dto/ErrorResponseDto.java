package com.url.shortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponseDto {
    private HttpStatus statusCode;
    private String errorMessage;
}
