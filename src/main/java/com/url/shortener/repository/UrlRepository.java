package com.url.shortener.repository;

import com.url.shortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByUrlId(String urlId);
    void deleteByExpirationDateBefore(LocalDateTime now); // Deletes expired URLs
}
