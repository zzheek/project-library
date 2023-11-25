package com.project_library.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    private String memberid;    // 회원 아이디
    private String membername;  // 회원 이름
    private String memberpass;  // 회원 비밀번호
    private String memberphone; // 회원 전화번호
    private String memberemail; // 회원 이메일

    public void setMemberpass(String encryptedPassword) {
        this.memberpass = encryptedPassword;
    }
}
