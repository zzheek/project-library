package com.project_library.service;

import com.project_library.domain.BookHistory;
import com.project_library.dto.BookHistoryDTO;
import com.project_library.repository.BookHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookHistoryServiceImpl implements BookHistoryService{

    private final BookHistoryRepository bookHistoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<BookHistoryDTO> findBookHistory(Long bookid) {
        List<BookHistory> bookHistory = bookHistoryRepository.findByBook_Bookid(bookid);

        return bookHistory.stream()
                .map(history -> modelMapper.map(history, BookHistoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void returnbook(Long bookhistoryid) {
        Optional<BookHistory> result = bookHistoryRepository.findById(bookhistoryid);
        if (result.isPresent()) {
            BookHistory bookHistory = result.get();
            bookHistory.setBookduedate(LocalDate.now());
            bookHistoryRepository.save(bookHistory);
        }

    }

}
