package com._5.basic.repository;

import com._5.basic.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    public boolean existsByName(String name);
    @Query(value = "SELECT * FROM authors WHERE name LIKE %?1%", nativeQuery = true)
    List<Author> searchByName(@Param("name") String name);

}
