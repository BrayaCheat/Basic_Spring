package com._5.basic.service.serviceImpl;

import com._5.basic.dto.mapper.AuthorMapper;
import com._5.basic.dto.request.AuthorRequestDTO;
import com._5.basic.dto.response.AuthorResponseDTO;
import com._5.basic.model.Author;
import com._5.basic.service.AuthorService;
import com._5.basic.repository.AuthorRepository;
import com._5.basic.utils.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final FileService fileService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper, FileService fileService) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.fileService = fileService;
    }

    @Override
    public Page<AuthorResponseDTO> listAuthors(Pageable pageable) {
        return authorRepository
                .findAll(pageable)
                .map(authorMapper::toDTO);
    }

    @Override
    public AuthorResponseDTO getAuthor(Long id) {
        Author author = authorRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found."));
        return authorMapper.toDTO(author);
    }

    @Override
    public AuthorResponseDTO createAuthor(AuthorRequestDTO dto, MultipartFile file) throws IOException {
        boolean exist = authorRepository.existsByName(dto.getName());
        if (exist) {
            throw new RuntimeException("Author already exist.");
        }
        String fileName = fileService.uploadFile(file);
        if(fileName.isEmpty()){
            throw new RuntimeException("File upload fail.");
        }
        Author author = Author.builder()
                .name(dto.getName())
                .birth(dto.getBirth())
                .image(fileName)
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
