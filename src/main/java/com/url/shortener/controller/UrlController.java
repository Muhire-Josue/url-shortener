package com.url.shortener.controller;

import com.url.shortener.dto.CreateShortUrlDto;
import com.url.shortener.entity.Url;
import com.url.shortener.service.IUrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UrlController {

    private final IUrlService service;

    public UrlController(IUrlService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CreateShortUrlDto> createUrl(@RequestBody Url url) {
        CreateShortUrlDto createdUrl = service.createShortUrl(url);
        return new ResponseEntity<>(createdUrl, HttpStatus.CREATED);
    }
}
