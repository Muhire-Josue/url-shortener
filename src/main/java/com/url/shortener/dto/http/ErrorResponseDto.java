package com.url.shortener.dto.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponseDto {
    private Integer statusCode;
    private String errorMessage;
}
