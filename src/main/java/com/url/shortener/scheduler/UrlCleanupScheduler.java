package com.url.shortener.scheduler;

import com.url.shortener.repository.UrlRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UrlCleanupScheduler {

    private final UrlRepository urlRepository;

    public UrlCleanupScheduler(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    // Run every minute to clean up expired URLs
    @Scheduled(fixedRate = 60000) // Every 60 seconds
    public void cleanUpExpiredUrls() {
        LocalDateTime now = LocalDateTime.now();
        urlRepository.deleteByExpirationDateBefore(now);
    }
}
