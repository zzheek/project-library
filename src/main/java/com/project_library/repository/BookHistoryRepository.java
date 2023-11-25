package com.project_library.repository;

import com.project_library.domain.Book;
import com.project_library.domain.BookHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookHistoryRepository extends JpaRepository<BookHistory, Long> {

    List<BookHistory> findByBook_Bookid(Long book);


}
