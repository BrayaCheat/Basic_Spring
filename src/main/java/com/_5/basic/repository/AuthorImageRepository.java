package com._5.basic.repository;

import com._5.basic.model.AuthorImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorImageRepository extends JpaRepository<AuthorImage, Long> {
    @Query(value = "select * from authors_images where author_id = :authorId", nativeQuery = true)
    List<AuthorImage> getAuthorImages(@Param("authorId") Long authorId);
}
