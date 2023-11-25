package com.project_library.repository;

import com.project_library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByBookid(Long bookid);


}
