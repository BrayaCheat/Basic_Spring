package com._5.basic.service;

import com._5.basic.model.Author;
import java.util.List;

public interface AuthorService {
    List<Author> listAuthors();
    Author getAuthor(Long id);
    Author createAuthor(Author author);
    Author updateAuthor(Long id, Author author);
    void deleteAuthor(Long id);
    List<Author> searchAuthor(String name);
}


