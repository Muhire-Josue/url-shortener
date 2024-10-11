package com.url.shortener.service;

import com.url.shortener.dto.CreateShortUrlDto;
import com.url.shortener.entity.Url;
import com.url.shortener.exception.DataConflictException;
import com.url.shortener.repository.UrlRepository;
import com.url.shortener.transfomer.UrlTransformer;
import com.url.shortener.util.CodeGenerator;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        if (url.getUrlId() == null) {
            String shortUrl = codeGenerator.generateUniqueShortUrl();
            url.setUrlId(shortUrl);
            url.setShortUrl("www.shortly.dev/" + shortUrl);
        } else {
            Optional<Url> foundUrl = repository.findByUrlId(url.getUrlId());
            if (foundUrl.isPresent()){
                throw new DataConflictException("URL ID Already exist.");
            }
        }

        Url savedUrl = repository.save(url);
        return transformer.mapEntityToDto(savedUrl);
    }
}
