package com.url.shortener.transfomer;

import com.url.shortener.dto.CreateShortUrlDto;
import com.url.shortener.entity.Url;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UrlTransformer {

    public Url mapDtoToEntity(CreateShortUrlDto dto){
        Url entity = new Url();
        entity.setOriginalUrl(dto.getOriginalUrl());
        entity.setShortUrl(dto.getShortUrl());
        entity.setCreatedDate(LocalDate.now());

        return entity;
    }

    public CreateShortUrlDto mapEntityToDto(Url entity) {
        CreateShortUrlDto dto = new CreateShortUrlDto();
        dto.setId(entity.getId());
        dto.setOriginalUrl(entity.getOriginalUrl());
        dto.setOriginalUrl(entity.getOriginalUrl());

        return dto;
    }
}
