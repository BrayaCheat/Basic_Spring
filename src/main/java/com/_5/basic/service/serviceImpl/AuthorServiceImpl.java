package com._5.basic.service.serviceImpl;

import com._5.basic.model.Author;
import com._5.basic.service.AuthorService;
import com._5.basic.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthor(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found."));
    }

    @Override
    public Author createAuthor(Author author){
        boolean exist = authorRepository.existsByName(author.getName());
        if(exist){
            throw new RuntimeException("Author already exist");
        }
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        Author findAuthor = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found."));
        Author updatedAuthor = findAuthor.toBuilder()
                .name(author.getName() != null ? author.getName() : findAuthor.getName())
                .DOB(author.getDOB() != null ? author.getDOB() : findAuthor.getDOB())
                .build();
        return authorRepository.save(updatedAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author findAuthor = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found."));
        authorRepository.deleteById(findAuthor.getId());
    }

    @Override
    public List<Author> searchAuthor(String name) {
        List<Author> ListAuthors = authorRepository.searchByName(name);
        if(ListAuthors.isEmpty()){
            throw new RuntimeException("No authors match this name.");
        }
        return ListAuthors;
    }
}
