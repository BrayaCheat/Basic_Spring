package com._5.basic.service.serviceImpl;

import com._5.basic.dto.mapper.AuthorProfileMapper;
import com._5.basic.dto.request.AuthorProfileRequestDTO;
import com._5.basic.dto.response.AuthorProfileResponseDTO;
import com._5.basic.model.Author;
import com._5.basic.model.AuthorProfile;
import com._5.basic.repository.AuthorProfileRepository;
import com._5.basic.repository.AuthorRepository;
import com._5.basic.service.AuthorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthorProfileServiceImpl implements AuthorProfileService {

    private final AuthorProfileRepository authorProfileRepository;
    private final AuthorProfileMapper authorProfileMapper;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorProfileServiceImpl(AuthorProfileRepository authorProfileRepository, AuthorProfileMapper authorProfileMapper, AuthorRepository authorRepository) {
        this.authorProfileRepository = authorProfileRepository;
        this.authorProfileMapper = authorProfileMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorProfileResponseDTO createAuthorProfile(Long authorId, AuthorProfileRequestDTO dto) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
        if (author.getAuthorProfile() != null) {
            throw new IllegalStateException("Author already have profile");
        }
        AuthorProfile authorProfile = AuthorProfile.builder()
                .story(dto.getStory())
                .author(author)
                .build();
        author.setAuthorProfile(authorProfile);
        return authorProfileMapper.toDTO(authorProfileRepository.save(authorProfile));
    }

    @Override
    public Optional<AuthorProfileResponseDTO> getAuthorProfile(Long authorId, Long profileId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
        Optional<AuthorProfile> authorProfile = authorProfileRepository
                .findById(profileId)
                .filter(x -> x.getAuthor().getId().equals(authorId));
        if (authorProfile.isEmpty()) {
            throw new RuntimeException(author.getName() + " does not have any profile.");
        }
        return authorProfile.map(authorProfileMapper::toDTO);
    }
}
