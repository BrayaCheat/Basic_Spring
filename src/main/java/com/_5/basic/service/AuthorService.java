package com._5.basic.service;

import com._5.basic.dto.request.AuthorRequestDTO;
import com._5.basic.dto.response.AuthorResponseDTO;
import java.util.List;

public interface AuthorService {
    List<AuthorResponseDTO> listAuthors();
    AuthorResponseDTO getAuthor(Long id);
    AuthorResponseDTO createAuthor(AuthorRequestDTO dto);
    AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO dto);
    void deleteAuthor(Long id);
    List<AuthorResponseDTO> searchAuthor(String name);
}


