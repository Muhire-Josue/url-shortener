package com.url.shortener.controller;

import com.url.shortener.dto.CreateShortUrlDto;
import com.url.shortener.dto.DeleteShortUrlDto;
import com.url.shortener.dto.ShortUrlResponseDto;
import com.url.shortener.entity.Url;
import com.url.shortener.exception.ResourceNotFoundException;
import com.url.shortener.repository.UrlRepository;
import com.url.shortener.service.IUrlService;
import com.url.shortener.transfomer.UrlTransformer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class UrlController {

    private final IUrlService service;
    private final UrlTransformer transformer;

    private final UrlRepository repository;

    public UrlController(IUrlService service, UrlTransformer transformer, UrlRepository repository) {
        this.service = service;
        this.transformer = transformer;
        this.repository = repository;
    }

    @PostMapping("/create")
    public ResponseEntity<ShortUrlResponseDto> createUrl(@Valid @RequestBody CreateShortUrlDto formData) {
        Url url = transformer.mapDtoToEntity(formData);
        CreateShortUrlDto createdUrl = service.createShortUrl(url);
        ShortUrlResponseDto responseDto = new ShortUrlResponseDto(createdUrl.getId(),
                createdUrl.getOriginalUrl(),
                createdUrl.getShortUrl(),
                createdUrl.getTtl(),
                createdUrl.getUrlId());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{urlId}")
    public ResponseEntity<String> deleteUrl(@Valid @PathVariable String urlId){
        Optional<Url> foundUrl = repository.findByUrlId(urlId);
        if (foundUrl.isEmpty()){
            throw new ResourceNotFoundException("Url can not be deleted because it does not exist");
        }
            repository.deleteByUrlId(urlId);
            return new ResponseEntity<>("URL deleted successfully", HttpStatus.NO_CONTENT);
    }
}
