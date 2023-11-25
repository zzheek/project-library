package com.project_library.service;

import com.project_library.domain.Member;
import com.project_library.dto.BookDTO;
import com.project_library.dto.MemberDTO;
import jakarta.transaction.Transactional;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.Optional;

public interface MemberService {

    Map<String, String> validateHandling(BindingResult bindingResult);  // 유효성 검사 에러 처리

    @Transactional
    void join(MemberDTO memberDTO);

}
