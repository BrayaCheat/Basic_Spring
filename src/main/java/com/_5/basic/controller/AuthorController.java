package com._5.basic.controller;

import com._5.basic.dto.request.AuthorRequestDTO;
import com._5.basic.dto.response.AuthorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com._5.basic.service.serviceImpl.AuthorServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuthorController {

    private final AuthorServiceImpl authorService;

    @Autowired
    public AuthorController(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorResponseDTO>> listAuthors() {
        return ResponseEntity.ok(authorService.listAuthors());
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthor(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthor(id));
    }

    @PostMapping("/authors")
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody AuthorRequestDTO dto) {
        return ResponseEntity.ok(authorService.createAuthor(dto));
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequestDTO dto) {
        return ResponseEntity.ok(authorService.updateAuthor(id, dto));
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok("Author deleted.");
    }

    @GetMapping("/authors/search")
    public ResponseEntity<List<AuthorResponseDTO>> searchAuthor(@RequestParam String name) {
        return ResponseEntity.ok(authorService.searchAuthor(name));
    }
}
