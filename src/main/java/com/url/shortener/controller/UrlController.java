package com.url.shortener.controller;

import com.url.shortener.dto.CreateShortUrlDto;
import com.url.shortener.dto.ShortUrlResponseDto;
import com.url.shortener.entity.Url;
import com.url.shortener.service.IUrlService;
import com.url.shortener.transfomer.UrlTransformer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class UrlController {

    private final IUrlService service;
    private final UrlTransformer transformer;

    public UrlController(IUrlService service, UrlTransformer transformer) {
        this.service = service;
        this.transformer = transformer;
    }

    @PostMapping("/create")
    public ResponseEntity<ShortUrlResponseDto> createUrl(@Valid @RequestBody CreateShortUrlDto formData) {
        Url url = transformer.mapDtoToEntity(formData);
        CreateShortUrlDto createdUrl = service.createShortUrl(url);
        ShortUrlResponseDto responseDto = new ShortUrlResponseDto(createdUrl.getId(),
                createdUrl.getOriginalUrl(),
                createdUrl.getShortUrl(),
                createdUrl.getTtl());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
