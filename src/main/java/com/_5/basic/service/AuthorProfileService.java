package com._5.basic.service;

import com._5.basic.dto.request.AuthorProfileRequestDTO;
import com._5.basic.dto.response.AuthorProfileResponseDTO;
import com._5.basic.dto.response.AuthorResponseDTO;

import java.util.Optional;

public interface AuthorProfileService {
    AuthorProfileResponseDTO createAuthorProfile(Long authorId, AuthorProfileRequestDTO dto);
    Optional<AuthorProfileResponseDTO> getAuthorProfile(Long authorId, Long profileId);
}
