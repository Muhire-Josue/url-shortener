package com.url.shortener.dto.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateShortUrlResponseDto {
    private Long id;
    private String originalUrl;
    private String shortUrl;
    private Long ttl;
    private String urlId;
}
