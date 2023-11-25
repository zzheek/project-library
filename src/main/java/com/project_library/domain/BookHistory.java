package com.project_library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BookHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookhistoryid; // 도서 이력 아이디

    @ManyToOne
    @JoinColumn(name = "bookid")
    private Book book;

    private String memberid;        // 회원 아이디

    @CreatedDate
    @Column(name = "bookloandate", updatable = false)
    private LocalDate bookloandate;    // 도서 대출일자

    private LocalDate bookduedate;     // 도서 반납기한


    public void setMemberid(String memberid) {

        this.memberid = memberid;
    }


    public void setBookduedate(LocalDate bookduedate) {

        this.bookduedate = bookduedate;
    }


}
