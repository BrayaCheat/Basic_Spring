package com._5.basic.service;

import com._5.basic.dto.request.BookRequestDTO;
import com._5.basic.dto.response.BookResponseDTO;
import com._5.basic.model.Book;

import java.util.List;

public interface BookService {
    List<BookResponseDTO> listBooks();
    BookResponseDTO getBook(Long id);
    BookResponseDTO createBook(BookRequestDTO dto);
    BookResponseDTO updateBook(Long id, BookRequestDTO dto);
    void deleteBook(Long id);
    List<BookResponseDTO> searchBook(String name);
}
