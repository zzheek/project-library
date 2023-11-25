package com.project_library.controller;

import com.project_library.domain.Member;
import com.project_library.dto.BookDTO;
import com.project_library.dto.BookHistoryDTO;
import com.project_library.repository.BookRepository;
import com.project_library.service.BookHistoryService;
import com.project_library.service.BookService;
import com.project_library.service.BookServiceImpl;
import com.project_library.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@Log4j2
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookHistoryService bookHistoryService;
    private final MemberService memberService;
    private final BookRepository bookRepository;


    // 도서 리스트
    @GetMapping("/list")
    public void list(Model model) {

        List<BookDTO> listAll = bookService.findAll();

        model.addAttribute("bookList", listAll);

    }

    // 도서 등록 화면
    @GetMapping("/register")
    public void registerGet() {


    }

    // 도서 등록
    @PostMapping("/register")
    public String register(@Valid BookDTO bookDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            // Validation 에러가 있을 경우 처리
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/book/register";
        }

        // 정상적인 경우 서비스 호출
        Long bookid = bookService.register(bookDTO);

        redirectAttributes.addFlashAttribute("result", bookid);

        return "redirect:/book/list";
    }

    // 도서 상세 화면
    @GetMapping("/detail")
    public String detail(Long bookid, Model model) {

        BookDTO bookDTO = bookService.detail(bookid);
        model.addAttribute("dto", bookDTO);

        return "/book/detail";

    }

    // 도서 대출
    @PostMapping("/borrow")
    public String borrow(@RequestParam Long bookid, @RequestParam String memberid) {

        bookService.borrow(bookid, memberid);


        return "redirect:/book/list";
    }


    // 도서 수정 화면
    @GetMapping("/modify")
    public String modifyGet(Long bookid, Model model) {

        BookDTO bookDTO = bookService.detail(bookid);
        model.addAttribute("dto", bookDTO);

        return "/book/modify";
    }

    // 도서 수정
    @PostMapping("/modify")
    public String modify(BookDTO bookDTO, RedirectAttributes redirectAttributes) {

        bookService.modify(bookDTO);
        redirectAttributes.addFlashAttribute("result", "modified");

        return "redirect:/book/detail?bookid=" + bookDTO.getBookid();


    }

    // 도서 대출 이력 화면
    @GetMapping("/history/{bookid}")
    public String history(@PathVariable Long bookid, Model model) {

        List<BookHistoryDTO> bookHistory = bookHistoryService.findBookHistory(bookid);
        model.addAttribute("bookHistory", bookHistory);
        model.addAttribute("bookid", bookid);  // 이 부분 추가


        return "/book/history";
    }

    // 도서 반납
    @PostMapping("/return/{bookid}")
    public String returnBook(@PathVariable Long bookid) {

        bookService.returnbook(bookid);
        bookHistoryService.returnbook(bookid);

        // 반납 후 이동할 페이지
        return "redirect:/book/list";
    }






}
