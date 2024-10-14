package com.url.shortener.controller;

import com.url.shortener.dto.CreateShortUrlDto;
import com.url.shortener.dto.http.CreateShortUrlResponseDto;
import com.url.shortener.dto.http.DeleteShortUrlResponseDto;
import com.url.shortener.entity.Url;
import com.url.shortener.exception.ResourceNotFoundException;
import com.url.shortener.repository.UrlRepository;
import com.url.shortener.service.IUrlService;
import com.url.shortener.transfomer.UrlTransformer;
import com.url.shortener.validator.UrlIdValidator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
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
    public ResponseEntity<CreateShortUrlResponseDto> createUrl(@Valid @RequestBody CreateShortUrlDto formData) {
        Url url = transformer.mapDtoToEntity(formData);
        CreateShortUrlDto createdUrl = service.createShortUrl(url);
        CreateShortUrlResponseDto responseDto = new CreateShortUrlResponseDto(createdUrl.getId(),
                createdUrl.getOriginalUrl(),
                createdUrl.getTtl(),
                createdUrl.getUrlId());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{urlId}")
    public RedirectView redirectToOriginalUrl(@PathVariable String urlId) {

        Optional<Url> foundUrl = repository.findByUrlId(urlId);

        if (foundUrl.isEmpty()) {
            log.warn("URL with ID {} not found.", urlId);
            throw new ResourceNotFoundException("URL with ID " + urlId + " not found.");
        }

        // Redirect to the original URL
        return new RedirectView(foundUrl.get().getOriginalUrl());
    }

    @DeleteMapping("/delete/{urlId}")
    public ResponseEntity<DeleteShortUrlResponseDto> deleteUrl(@PathVariable String urlId){
        // Validate the URL ID
        UrlIdValidator.validateUrlId(urlId);

        System.out.println("HERER");
        Optional<Url> foundUrl = repository.findByUrlId(urlId);

        if (foundUrl.isEmpty()) {
            log.warn("URL with ID {} not found for deletion.", urlId);
            throw new ResourceNotFoundException("URL with ID " + urlId + " not found.");
        }

        repository.deleteByUrlId(urlId);
        return new ResponseEntity<>(new DeleteShortUrlResponseDto("URL deleted successfully."), HttpStatus.OK);
    }
}
