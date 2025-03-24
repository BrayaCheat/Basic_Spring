package com._5.basic.service;

import com._5.basic.dto.request.BookRequestDTO;
import com._5.basic.dto.response.BookResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Page<BookResponseDTO> listBooks(Pageable pageable);
    BookResponseDTO getBook(Long id);
    BookResponseDTO createBook(Long authorId, BookRequestDTO dto);
    BookResponseDTO updateBook(Long id, BookRequestDTO dto);
    void deleteBook(Long id);
    List<BookResponseDTO> searchBook(String name);
    Optional<BookResponseDTO> getBookByAuthor(Long authorId, Long bookId);
}
