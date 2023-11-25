package com.project_library.security;

import com.project_library.domain.Member;
import com.project_library.repository.MemberRepository;
import com.project_library.security.dto.MemberDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> result = memberRepository.findByMemberid(username);

        if (!result.isPresent()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
        }

        Member member = result.get();

        MemberDetailsDTO memberDetailsDTO = new MemberDetailsDTO(
                member.getMemberid(),
                member.getMemberpass(),
                member.getMembername(),
                member.getMemberemail(),
                member.getMemberphone()
        );

        return memberDetailsDTO;
    }
}
