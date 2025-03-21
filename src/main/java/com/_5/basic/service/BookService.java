package com._5.basic.service;

import com._5.basic.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listBooks();
    Book getBook(Long id);
    Book createBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    List<Book> searchBook(String name);
}
