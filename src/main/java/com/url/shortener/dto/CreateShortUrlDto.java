package com.url.shortener.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateShortUrlDto {
    private Long id;
    private String originalUrl;
    private String shortUrl;

}
