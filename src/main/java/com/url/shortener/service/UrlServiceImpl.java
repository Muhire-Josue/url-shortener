package com.url.shortener.service;

import com.url.shortener.dto.CreateShortUrlDto;
import com.url.shortener.entity.Url;
import com.url.shortener.repository.UrlRepository;
import com.url.shortener.transfomer.UrlTransformer;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements IUrlService{
    private final UrlRepository repository;
    private final UrlTransformer transformer;

    public UrlServiceImpl(UrlRepository repository, UrlTransformer transformer) {
        this.repository = repository;
        this.transformer = transformer;
    }

    @Override
    public CreateShortUrlDto createShortUrl(Url url) {
        Url savedUrl = repository.save(url);
        return transformer.mapEntityToDto(savedUrl);
    }
}
