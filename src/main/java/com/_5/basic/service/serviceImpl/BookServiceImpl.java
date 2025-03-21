package com._5.basic.service.serviceImpl;

import com._5.basic.model.Author;
import com._5.basic.model.Book;
import com._5.basic.repository.AuthorRepository;
import com._5.basic.repository.BookRepository;
import com._5.basic.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found."));
    }

    @Override
    public Book createBook(Book book) {
        Author findAuthor = authorRepository.findById(book.getAuthor().getId()).orElseThrow(() -> new RuntimeException("Author not found."));
        book.setAuthor(findAuthor);
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Book findBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found."));
        Book updatedBook = findBook.toBuilder()
                .name(book.getName() != null ? book.getName() : findBook.getName())
                .publishDate(book.getPublishDate() != null ? book.getPublishDate() : findBook.getPublishDate())
                .build();
        return bookRepository.save(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
       bookRepository.deleteById(id);
    }

    @Override
    public List<Book> searchBook(String name) {
        return bookRepository.searchBook(name);
    }
}
