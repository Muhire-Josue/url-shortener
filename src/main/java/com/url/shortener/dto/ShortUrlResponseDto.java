package com.url.shortener.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ShortUrlResponseDto {
    private Long id;
    private String originalUrl;
    private String shortUrl;
    private Long ttl;
    private String urlId;
}
