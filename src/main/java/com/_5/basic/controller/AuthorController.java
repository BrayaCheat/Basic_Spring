package com._5.basic.controller;

import com._5.basic.dto.request.AuthorRequestDTO;
import com._5.basic.dto.response.AuthorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com._5.basic.service.serviceImpl.AuthorServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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
    public ResponseEntity<Page<AuthorResponseDTO>> listAuthors(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return ResponseEntity.ok(authorService.listAuthors(pageable));
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthor(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthor(id));
    }

    @PostMapping(value = "/authors", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createAuthor(@ModelAttribute AuthorRequestDTO dto, @RequestParam List<MultipartFile> files) throws IOException {
//        return ResponseEntity.status(201).body(files.stream().count());
        return ResponseEntity.ok(authorService.createAuthor(dto, files));
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
