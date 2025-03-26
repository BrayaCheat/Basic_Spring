package com._5.basic.service.serviceImpl;

import com._5.basic.model.Author;
import com._5.basic.model.AuthorImage;
import com._5.basic.repository.AuthorImageRepository;
import com._5.basic.repository.AuthorRepository;
import com._5.basic.service.AuthorImageService;
import com._5.basic.utils.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorImageServiceImpl implements AuthorImageService {

    private final AuthorImageRepository authorImageRepository;
    private final AuthorRepository authorRepository;
    private final FileService fileService;

    @Autowired
    public AuthorImageServiceImpl(AuthorImageRepository authorImageRepository, AuthorRepository authorRepository, FileService fileService){
        this.authorImageRepository = authorImageRepository;
        this.authorRepository = authorRepository;
        this.fileService = fileService;
    }

    @Override
    public List<AuthorImage> uploadAuthorImages(Long authorId, List<MultipartFile> files) throws IOException {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
        List<AuthorImage> authorImages = new ArrayList<>();
        for(MultipartFile file : files){
            if(!file.isEmpty()){
                String imageUrl = fileService.uploadFile(file);
                AuthorImage authorImage = AuthorImage.builder()
                        .url(imageUrl)
                        .author(author)
                        .build();
                authorImages.add(authorImage);
            }
        }
        return authorImageRepository.saveAll(authorImages);
    }

    @Override
    public List<AuthorImage> getAuthorImages(Long authorId){
        if(!authorRepository.existsById(authorId)){
            throw new RuntimeException("Author not found");
        }
        return authorImageRepository.getAuthorImages(authorId);
    }

    @Override
    public AuthorImage updateAuthorImage(Long authorId, Long imageId, MultipartFile file) throws IOException {
        if (!authorRepository.existsById(authorId)) {
            throw new RuntimeException("Author not found");
        }
        if (file.isEmpty()) {
            throw new RuntimeException("File not found");
        }
        AuthorImage authorImage = authorImageRepository.findById(imageId).orElseThrow(() -> new RuntimeException("Image not found"));
        String fileUrl = fileService.uploadFile(file);
        authorImage.setUrl(fileUrl != null ? fileUrl : authorImage.getUrl());
        return authorImageRepository.save(authorImage);
    }
}
