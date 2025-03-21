package com._5.basic.service.serviceImpl;

import com._5.basic.dto.mapper.BookMapper;
import com._5.basic.dto.request.BookRequestDTO;
import com._5.basic.dto.response.BookResponseDTO;
import com._5.basic.model.Author;
import com._5.basic.model.Book;
import com._5.basic.repository.AuthorRepository;
import com._5.basic.repository.BookRepository;
import com._5.basic.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, BookMapper bookMapper){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookResponseDTO> listBooks() {
        return bookRepository
                .findAll()
                .stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDTO getBook(Long id) {
        Book book = bookRepository
                    .findById(id)
                    .orElseThrow(() -> new RuntimeException("Book not found"));
        return bookMapper.toDTO(book);
    }

    @Override
    public BookResponseDTO createBook(BookRequestDTO dto) {
        Author findAuthor = authorRepository.findById(dto.getAuthorId()).orElseThrow(() -> new RuntimeException("Author not found."));

        Book book = Book.builder()
                .name(dto.getName())
                .publishDate(dto.getPublishDate())
                .author(findAuthor)
                .build();

        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    @Override
    public BookResponseDTO updateBook(Long id, BookRequestDTO dto) {
        Book findBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found."));
        if(dto.getName() != null){
            findBook.setName(dto.getName());
        }
        if(dto.getPublishDate() != null){
            findBook.setPublishDate(dto.getPublishDate());
        }
        Book savedBook = bookRepository.save(findBook);
        return bookMapper.toDTO(savedBook);
    }

    @Override
    public void deleteBook(Long id) {
        if(!bookRepository.existsById(id)){
            throw new RuntimeException("Book not found.");
        }
       bookRepository.deleteById(id);
    }

    @Override
    public List<BookResponseDTO> searchBook(String name) {
        List<Book> books = bookRepository.searchBook(name);
        if(books.isEmpty()){
            throw new RuntimeException("No books found.");
        }
        return books
                .stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }
}
