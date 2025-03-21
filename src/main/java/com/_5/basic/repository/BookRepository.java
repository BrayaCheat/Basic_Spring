package com._5.basic.repository;

import com._5.basic.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "select * from books where name like %?1%", nativeQuery = true)
    public List<Book> searchBook(@Param("name") String name);
}
