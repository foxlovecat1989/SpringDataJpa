package com.edmoresby.springdatajpa.book;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.student.id = ?1")
    List<Book> findBooksByStudentId(Long studentId);
}
