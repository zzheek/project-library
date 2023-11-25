package com.project_library.service;

import com.project_library.domain.Book;
import com.project_library.domain.BookHistory;
import com.project_library.domain.Member;
import com.project_library.dto.BookDTO;
import com.project_library.dto.MemberDTO;
import com.project_library.repository.BookHistoryRepository;
import com.project_library.repository.BookRepository;
import com.project_library.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;


    @Override
    public Map<String, String> validateHandling(BindingResult bindingResult) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : bindingResult.getFieldErrors()) {
            String validKeyName = "valid_" + error.getField();
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    @Override
    public void join(MemberDTO memberDTO) {

        Member member = modelMapper.map(memberDTO, Member.class);

        String encryptedPassword = passwordEncoder.encode(memberDTO.getMemberpass());
        member.setMemberpass(encryptedPassword);  // 암호화된 비밀번호로 설정

        memberRepository.save(member);


    }

}
