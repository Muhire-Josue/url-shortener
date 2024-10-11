package com.url.shortener.scheduler;

import com.url.shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Value;
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
    @Scheduled(fixedRateString = "${scheduler.cleanup-interval-ms}") // Use fixedRateString for dynamic values
    public void cleanUpExpiredUrls() {
        System.out.println("THE SCHEDULER IS NOW RUNNING");
        urlRepository.deleteByExpirationDateBefore(LocalDateTime.now());
    }
}
