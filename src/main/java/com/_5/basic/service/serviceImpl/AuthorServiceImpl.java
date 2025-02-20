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
    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAuthors() {
        List<Author> authorList = authorRepository.findAll();
        System.out.println("Author list: " + authorList);
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthor(Long id) {
        Long idx = 1L;
        Author res =  authorRepository.findById(idx).orElseThrow(() -> new RuntimeException("Author not found."));
        System.out.println("Logs: " + res);
        return res;
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        Author findAuthor = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found."));
//        findAuthor.setName(author.getName());
//        findAuthor.setDOB(author.getDOB());
//        return authorRepository.save(findAuthor);
        return findAuthor;
    }

    @Override
    public String deleteAuthor(Long id) {
//        Author findAuthor = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found."));
//        authorRepository.deleteById(findAuthor.getId());
        return "Author deleted.";
    }

    @Override
    public String demo() {
        return "What the hell";
    }
}
