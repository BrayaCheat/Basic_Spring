package com._5.basic.service;

import com._5.basic.model.AuthorImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AuthorImageService {
    List<AuthorImage> uploadAuthorImages(Long authorId, List<MultipartFile> files) throws IOException;
    List<AuthorImage> getAuthorImages(Long authorId);
    AuthorImage updateAuthorImage(Long authorId, Long imageId, MultipartFile file) throws IOException;
}
