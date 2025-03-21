package com._5.basic.controller;

import com._5.basic.model.Book;
import com._5.basic.service.serviceImpl.BookServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookServiceImpl bookService;

    @Autowired
    public BookController(BookServiceImpl bookService){
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @GetMapping
    public ResponseEntity<List<Book>> listBooks(){
        return ResponseEntity.ok(bookService.listBooks());
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book){
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted.");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String name){
        return ResponseEntity.ok(bookService.searchBook(name));
    }
}
