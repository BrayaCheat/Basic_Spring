package com._5.basic.controller;

import com._5.basic.model.Author;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com._5.basic.service.serviceImpl.AuthorServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {

    private final AuthorServiceImpl authorService;

    @Autowired
    public AuthorController(AuthorServiceImpl authorService){
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> listAuthors(){
        return ResponseEntity.ok(authorService.listAuthors());
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id){
        return ResponseEntity.ok(authorService.getAuthor(id));
    }

    @PostMapping("/author")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author){
        return ResponseEntity.ok(authorService.createAuthor(author));
    }

    @PutMapping("/author/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author){
        return ResponseEntity.ok(authorService.updateAuthor(id, author));
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);
        return ResponseEntity.ok("Author deleted.");
    }

    @GetMapping("/author/search")
    public ResponseEntity<List<Author>> searchAuthor(@RequestParam String name){
        return ResponseEntity.ok(authorService.searchAuthor(name));
    }
}
