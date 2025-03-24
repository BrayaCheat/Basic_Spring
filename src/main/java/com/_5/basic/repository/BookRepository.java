package com._5.basic.repository;

import com._5.basic.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "select * from books where name like %:name%", nativeQuery = true)
    public List<Book> searchBook(@Param("name") String name);

    public Optional<Book> findByName(String name);

    @Query(value = "select * from books where author_id = :authorId and id = :bookId", nativeQuery = true)
    public Optional<Book> getBookByAuthor(@Param("authorId") Long authorId, @Param("bookId") Long bookId);
}
