package com.project_library.service;

import com.project_library.domain.Book;
import com.project_library.dto.BookDTO;
import com.project_library.dto.BookHistoryDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDTO> findAll();    // 도서 리스트 출력

    Long register(BookDTO bookDTO);

    BookDTO detail(Long bookid);

    void modify(BookDTO bookDTO);

    void borrow(Long bookid, String memberid);
    
    void returnbook(Long bookid);
}
