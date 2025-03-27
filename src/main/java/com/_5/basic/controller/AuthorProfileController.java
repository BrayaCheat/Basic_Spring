package com._5.basic.controller;

import com._5.basic.dto.request.AuthorProfileRequestDTO;
import com._5.basic.dto.response.AuthorProfileResponseDTO;
import com._5.basic.service.serviceImpl.AuthorProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AuthorProfileController {

    private AuthorProfileServiceImpl authorProfileService;

    @Autowired
    public AuthorProfileController(AuthorProfileServiceImpl authorProfileService){
        this.authorProfileService = authorProfileService;
    }

    @PostMapping("/authors/{authorId}/profile")
    public ResponseEntity<AuthorProfileResponseDTO> createAuthorProfile(@PathVariable Long authorId, @RequestBody AuthorProfileRequestDTO dto){
        return ResponseEntity.status(201).body(authorProfileService.createAuthorProfile(authorId, dto));
    }

    @GetMapping("/authors/{authorId}/profile/{profileId}")
    public ResponseEntity<Optional<AuthorProfileResponseDTO>> getAuthorProfile(@PathVariable Long authorId, @PathVariable Long profileId){
        return ResponseEntity.status(200).body(authorProfileService.getAuthorProfile(authorId, profileId));
    }
}
