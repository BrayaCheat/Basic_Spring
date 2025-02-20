package com._5.basic.service;

import com._5.basic.model.Author;
import java.util.List;

public interface AuthorService {
    List<Author> listAuthors();
    Author getAuthor(Long id);
    Author updateAuthor(Long id, Author author);
    String deleteAuthor(Long id);
}

