package com.alley.content_calendar.controller;

import com.alley.content_calendar.model.Content;
import com.alley.content_calendar.repository.ContentCollectionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentCollectionRepository repository;
    public ContentController(ContentCollectionRepository contentCollectionRepository){
        this.repository = contentCollectionRepository;
    }

    @GetMapping("")
    public List<Content> findAll(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Content findById(@PathVariable String id){
        return repository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content){
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Content content, @PathVariable Integer id ){
        if(!repository.existById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        repository.save(content);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id ){
        if(!repository.existById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        repository.remove(id);
    }
}
