package com._5.basic.service.serviceImpl;

import com._5.basic.dto.mapper.AuthorMapper;
import com._5.basic.dto.request.AuthorRequestDTO;
import com._5.basic.dto.response.AuthorResponseDTO;
import com._5.basic.model.Author;
import com._5.basic.model.AuthorImage;
import com._5.basic.repository.AuthorImageRepository;
import com._5.basic.service.AuthorService;
import com._5.basic.repository.AuthorRepository;
import com._5.basic.utils.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final FileService fileService;
    private final AuthorImageRepository authorImageRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper, FileService fileService, AuthorImageRepository authorImageRepository) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.fileService = fileService;
        this.authorImageRepository = authorImageRepository;
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
    public AuthorResponseDTO createAuthor(AuthorRequestDTO dto, List<MultipartFile> files) throws IOException {
        boolean exist = authorRepository.existsByName(dto.getName());
        if (exist) {
            throw new RuntimeException("Author already exist.");
        }
        if(files.isEmpty()){
            throw new RuntimeException("Files not found.");
        }

        // build author
        Author author = Author.builder()
                .name(dto.getName())
                .birth(dto.getBirth())
                .build();
        Author savedAuthor = authorRepository.save(author);

        // mapping image
        List<AuthorImage> authorImages = new ArrayList<>();
        for(MultipartFile file : files){
            if(!file.isEmpty()){
                String imageUrl = fileService.uploadFile(file);
                AuthorImage authorImage = AuthorImage.builder()
                        .url(imageUrl)
                        .author(savedAuthor)
                        .build();
                authorImages.add(authorImage);
            }
        }
        authorImageRepository.saveAll(authorImages);
        savedAuthor.setImages(authorImages);
        return authorMapper.toDTO(savedAuthor);
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
        if (!authorRepository.existsById(id)) {
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
