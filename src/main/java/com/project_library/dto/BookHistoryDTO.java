package com.project_library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookHistoryDTO {

    private Long      bookhistoryid; // 도서 이력 아이디
    private Long      bookid;
    private String    memberid;
    private LocalDate bookloandate;
    private LocalDate bookduedate;


}
