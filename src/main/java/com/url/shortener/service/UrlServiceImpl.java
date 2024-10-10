package com.url.shortener.service;

import com.url.shortener.dto.CreateShortUrlDto;
import com.url.shortener.entity.Url;
import com.url.shortener.repository.UrlRepository;
import com.url.shortener.transfomer.UrlTransformer;
import com.url.shortener.util.CodeGenerator;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements IUrlService{
    private final UrlRepository repository;
    private final UrlTransformer transformer;
    private final CodeGenerator codeGenerator;

    public UrlServiceImpl(UrlRepository repository, UrlTransformer transformer, CodeGenerator codeGenerator) {
        this.repository = repository;
        this.transformer = transformer;
        this.codeGenerator = codeGenerator;
    }

    @Override
    public CreateShortUrlDto createShortUrl(Url url) {
        if (url.getUrlId() != null) {
            String shortUrl = codeGenerator.generateUniqueShortUrl();
            url.setUrlId(shortUrl);
        }
        Url savedUrl = repository.save(url);
        return transformer.mapEntityToDto(savedUrl);
    }
}
