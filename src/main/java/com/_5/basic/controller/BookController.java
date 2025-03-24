package com._5.basic.controller;

import com._5.basic.dto.request.BookRequestDTO;
import com._5.basic.dto.response.BookResponseDTO;
import com._5.basic.service.serviceImpl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    private final BookServiceImpl bookService;

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @GetMapping("/books")
    public ResponseEntity<Page<BookResponseDTO>> listBooks(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return ResponseEntity.ok(bookService.listBooks(pageable));
    }

    @PostMapping("/authors/{authorId}/books")
    public ResponseEntity<BookResponseDTO> createBook(@PathVariable Long authorId, @RequestBody BookRequestDTO dto) {
        return ResponseEntity.ok(bookService.createBook(authorId, dto));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO dto) {
        return ResponseEntity.ok(bookService.updateBook(id, dto));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted.");
    }

    @GetMapping("/books/search")
    public ResponseEntity<List<BookResponseDTO>> searchBooks(@RequestParam String name) {
        return ResponseEntity.ok(bookService.searchBook(name));
    }

    @GetMapping("/authors/{authorId}/books/{bookId}")
    public ResponseEntity<Optional<BookResponseDTO>> getBookByAuthor(@PathVariable Long authorId, @PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getBookByAuthor(authorId, bookId));
    }
}
