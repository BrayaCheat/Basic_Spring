package com._5.basic.controller;

import com._5.basic.model.AuthorImage;
import com._5.basic.service.serviceImpl.AuthorImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuthorImageController {

    private final AuthorImageServiceImpl authorImageService;

    @Autowired
    public AuthorImageController(AuthorImageServiceImpl authorImageService){
        this.authorImageService = authorImageService;
    }

    @PostMapping("/authors/{authorId}/images")
    public ResponseEntity<List<AuthorImage>> uploadAuthorImages(@PathVariable Long authorId, @RequestParam List<MultipartFile> files) throws IOException {
        return ResponseEntity.status(201).body(authorImageService.uploadAuthorImages(authorId, files));
    }

    @GetMapping("/authors/{authorId}/images")
    public ResponseEntity<List<AuthorImage>> getAuthorImage(@PathVariable Long authorId){
        return ResponseEntity.status(200).body(authorImageService.getAuthorImages(authorId));
    }

    @PutMapping(value = "/authors/{authorId}/images/{imageId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AuthorImage> updateAuthorImage(@PathVariable Long authorId, @PathVariable Long imageId, @RequestParam MultipartFile files) throws IOException {
        return ResponseEntity.status(200).body(authorImageService.updateAuthorImage(authorId, imageId, files));
    }

}
