package com._5.basic.service;

import com._5.basic.dto.request.AuthorRequestDTO;
import com._5.basic.dto.response.AuthorResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {
    Page<AuthorResponseDTO> listAuthors(Pageable pageable);
    AuthorResponseDTO getAuthor(Long id);
    AuthorResponseDTO createAuthor(AuthorRequestDTO dto);
    AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO dto);
    void deleteAuthor(Long id);
    List<AuthorResponseDTO> searchAuthor(String name);
}


