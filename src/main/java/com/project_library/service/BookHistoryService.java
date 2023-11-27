package com.project_library.service;

import com.project_library.domain.BookHistory;
import com.project_library.dto.BookHistoryDTO;

import java.util.List;

public interface BookHistoryService {

    List<BookHistoryDTO> findBookHistory(Long bookid);

    void returnbook(Long bookhistoryid);
}
