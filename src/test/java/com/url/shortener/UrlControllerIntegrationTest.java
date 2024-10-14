package com.url.shortener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.url.shortener.dto.CreateShortUrlDto;
import com.url.shortener.dto.http.DeleteShortUrlResponseDto;
import com.url.shortener.entity.Url;
import com.url.shortener.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest // Starts the full Spring context for integration testing
@AutoConfigureMockMvc // Auto-configures MockMvc to simulate HTTP requests
public class UrlControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private ObjectMapper objectMapper; // To convert objects to JSON strings and vice versa

    @BeforeEach
    public void setup() {
        // Clear the database before each test
        urlRepository.deleteAll();
    }
    // Test: Create Short URL
    @Test
    public void shouldCreateShortUrl() throws Exception {
        CreateShortUrlDto createShortUrlDto = new CreateShortUrlDto();
        createShortUrlDto.setOriginalUrl("https://www.example.com");
        createShortUrlDto.setUrlId("abc123");
        createShortUrlDto.setTtl(3600L); // 1 hour TTL

        mockMvc.perform(post("/api/v1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createShortUrlDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.originalUrl").value("https://www.example.com"))
                .andExpect(jsonPath("$.urlId").value("abc123"));
    }
}
