package com._5.basic.service.serviceImpl;

import com._5.basic.dto.mapper.AuthorMapper;
import com._5.basic.dto.request.AuthorRequestDTO;
import com._5.basic.dto.response.AuthorResponseDTO;
import com._5.basic.model.Author;
import com._5.basic.service.AuthorService;
import com._5.basic.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<AuthorResponseDTO> listAuthors() {
        return authorRepository
                .findAll()
                .stream()
                .map(authorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponseDTO getAuthor(Long id) {
        Author author = authorRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found."));
        return authorMapper.toDTO(author);
    }

    @Override
    public AuthorResponseDTO createAuthor(AuthorRequestDTO dto) {
        boolean exist = authorRepository.existsByName(dto.getName());
        if (exist) {
            throw new RuntimeException("Author already exist.");
        }
        Author author = Author.builder()
                .name(dto.getName())
                .birth(dto.getBirth())
                .build();
        return authorMapper.toDTO(authorRepository.save(author));
    }

    @Override
    public AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO dto) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found."));
        if (dto.getName() != null) {
            author.setName(dto.getName());
        }
        if (dto.getBirth() != null) {
            author.setBirth(dto.getBirth());
        }
        return authorMapper.toDTO(authorRepository.save(author));
    }

    @Override
    public void deleteAuthor(Long id) {
        if(!authorRepository.existsById(id)){
            throw new RuntimeException("Author not found");
        }
        authorRepository.deleteById(id);
    }

    @Override
    public List<AuthorResponseDTO> searchAuthor(String name) {
        List<Author> ListAuthors = authorRepository.searchByName(name);
        if (ListAuthors.isEmpty()) {
            throw new RuntimeException("No authors match this name.");
        }
        return ListAuthors
                .stream()
                .map(authorMapper::toDTO)
                .collect(Collectors.toList());
    }
}
