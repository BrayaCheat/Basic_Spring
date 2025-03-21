package com._5.basic.controller;

import com._5.basic.dto.request.AuthorRequestDTO;
import com._5.basic.dto.response.AuthorResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com._5.basic.service.serviceImpl.AuthorServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {

    private static final Logger log = LoggerFactory.getLogger(AuthorController.class);
    private final AuthorServiceImpl authorService;

    @Autowired
    public AuthorController(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorResponseDTO>> listAuthors() {
        System.out.println("Log from controller: " + authorService.listAuthors());
        log.info("Log from controller: " + authorService.listAuthors());
        return ResponseEntity.ok(authorService.listAuthors());
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthor(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthor(id));
    }

    @PostMapping("/author")
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody AuthorRequestDTO dto) {
        return ResponseEntity.ok(authorService.createAuthor(dto));
    }

    @PutMapping("/author/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequestDTO dto) {
        return ResponseEntity.ok(authorService.updateAuthor(id, dto));
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok("Author deleted.");
    }

    @GetMapping("/author/search")
    public ResponseEntity<List<AuthorResponseDTO>> searchAuthor(@RequestParam String name) {
        return ResponseEntity.ok(authorService.searchAuthor(name));
    }
}
