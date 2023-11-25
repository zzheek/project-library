package com.project_library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    bookid;         // 도서 아이디

    @Column(length = 500, nullable = false)
    private String    booktitle;    // 도서 제목

    @Column(length = 500, nullable = false)
    private String    bookcontent;  // 도서 내용

    @Column(length = 500, nullable = false)
    private String    bookwriter;   // 도서 작가

    @CreatedDate
    @Column(name = "bookrdate", updatable = false)
    private LocalDate bookrdate;    // 도서 등록일자

    @LastModifiedDate
    @Column(name="bookmdate")
    private LocalDate bookmdate;    // 도서 수정일자

    @Builder.Default
    private Boolean   bookchk = false;       // 도서 대출 중인지 아닌지 확인 true,false

    public void setBookchk(Boolean bookchk) {
        this.bookchk = bookchk;
    }

    public void change(String booktitle, String bookcontent, String bookwriter) {
        this.booktitle   = booktitle;
        this.bookcontent = bookcontent;
        this.bookwriter  = bookwriter;

    }


}
