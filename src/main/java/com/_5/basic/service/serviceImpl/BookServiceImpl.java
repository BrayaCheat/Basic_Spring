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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public Page<BookResponseDTO> listBooks(Pageable pageable) {
        return bookRepository
                .findAll(pageable)
                .map(bookMapper::toDTO);
    }

    @Override
    public BookResponseDTO getBook(Long id) {
        Book book = bookRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Book with id: " + id + " not found."));
        return bookMapper.toDTO(book);
    }

    @Override
    public BookResponseDTO createBook(Long authorId, BookRequestDTO dto) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found."));
        Book book = Book.builder()
                .name(dto.getName())
                .publishDate(dto.getPublishDate())
                .author(author)
                .build();
        return bookMapper.toDTO(bookRepository.save(book));
    }

    @Override
    public BookResponseDTO updateBook(Long id, BookRequestDTO dto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found."));
        if (dto.getName() != null) {
            book.setName(dto.getName());
        }
        if (dto.getPublishDate() != null) {
            book.setPublishDate(dto.getPublishDate());
        }
        return bookMapper.toDTO(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found.");
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookResponseDTO> searchBook(String name) {
        List<Book> books = bookRepository.searchBook(name);
        if (books.isEmpty()) {
            throw new RuntimeException("No books found.");
        }
        return books
                .stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookResponseDTO> getBookByAuthor(Long authorId, Long bookId){
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));
        Optional<Book> book = bookRepository
                .findById(bookId)
                .filter(x -> x.getAuthor().getId().equals(authorId));
        if(book.isEmpty()){
            throw new RuntimeException(author.getName() + " does not have book id: " + bookId);
        }
        return book.map(bookMapper::toDTO);
    }
}
