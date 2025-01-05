package com.alley.content_calendar.repository;

import com.alley.content_calendar.model.Content;
import com.alley.content_calendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.alley.content_calendar.model.Status.IDEA;

@Repository
public class ContentCollectionRepository {
    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {
    }

    public List<Content> findAll() {
        return contentList;
    }

    public Optional<Content> findById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }


    public void save(Content content) {
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }

    @PostConstruct
    public void init() {
        Content c = new Content(
                1,
                "First Post",
                "This is my first post",
                IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                ""
        );
        Content c2 = new Content(
                2,
                "Second Post",
                "This is my Second post",
                IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                ""
        );
        contentList.add(c);
        contentList.add(c2);
    }

    public boolean existById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst().isPresent();
    }

    public void remove(Integer id) {
        Content getContent = contentList.stream().filter(c -> c.id().equals(id)).findFirst().get();
        contentList.remove(getContent);
    }
}
