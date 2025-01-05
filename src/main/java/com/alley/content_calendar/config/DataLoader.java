package com.alley.content_calendar.config;

import com.alley.content_calendar.model.Content;
import com.alley.content_calendar.repository.ContentCollectionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ContentCollectionRepository repository;
    private final ObjectMapper objectMapper;

    public DataLoader(ContentCollectionRepository contentRepository, ObjectMapper objectMapper) {
        this.repository = contentRepository;
        this.objectMapper = objectMapper;
    }

    public void run(String... args) throws Exception {
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/content.json")) {
            objectMapper.readValue(inputStream, new TypeReference<List<Content>>() {
            }).forEach(repository::save);
        }
    }
}
