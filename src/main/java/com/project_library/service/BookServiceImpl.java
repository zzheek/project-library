package com.project_library.service;

import com.project_library.domain.Book;
import com.project_library.domain.BookHistory;
import com.project_library.dto.BookDTO;
import com.project_library.dto.BookHistoryDTO;
import com.project_library.repository.BookHistoryRepository;
import com.project_library.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final BookHistoryRepository bookHistoryRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<BookDTO> findAll() {
        List<Book> allBook = bookRepository.findAll();

        return allBook.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long register(BookDTO bookDTO) {

        Book book = modelMapper.map(bookDTO, Book.class);
        Long bookid = bookRepository.save(book).getBookid();

        return bookid;
    }

    @Override
    public BookDTO detail(Long bookid) {
        Optional<Book> book = bookRepository.findByBookid(bookid);
        return modelMapper.map(book.orElse(new Book()), BookDTO.class);
    }

    @Override
    public void modify(BookDTO bookDTO) {

        Optional<Book> result = bookRepository.findByBookid(bookDTO.getBookid());

        Book book = result.orElseThrow();

        book.change(bookDTO.getBooktitle(), bookDTO.getBookcontent(), bookDTO.getBookwriter());

        bookRepository.save(book);
    }

    @Override
    public void borrow(Long bookid, String memberid) {
        Optional<Book> optionalBook = bookRepository.findById(bookid);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();

            // 도서의 대출 상태 변경
            book.setBookchk(true);
            bookRepository.save(book);

            // 대출 이력 추가
            BookHistory bookHistory = BookHistory.builder()
                    .book(book) // Book 엔터티 설정
                    .memberid(memberid)
                    .bookloandate(LocalDate.now())
                    .bookduedate(LocalDate.now().plusDays(14))
                    .build();

            bookHistoryRepository.save(bookHistory);
        }
    }

    @Override
    public void returnbook(Long bookid) {
        Optional<Book> optionalBook = bookRepository.findById(bookid);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();

            book.setBookchk(false);
            bookRepository.save(book);
        }
    }


}
