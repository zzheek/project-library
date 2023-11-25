package com.project_library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long      bookid;

    @NotBlank
    private String    booktitle;

    @NotBlank
    private String    bookcontent;

    @NotBlank
    private String    bookwriter;

    private LocalDate bookrdate;
    private LocalDate bookmdate;
    private Boolean   bookchk;



}
