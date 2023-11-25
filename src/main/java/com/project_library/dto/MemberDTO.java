package com.project_library.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MemberDTO {

    @NotBlank
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{6,20}$", message = "아이디는 특수문자를 제외한 6~20를 입력해주세요.")
    private String memberid;

    @NotBlank
    private String membername;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 8~20자 영문 대소문자, 숫자, 특수문자를 사용해주세요.")
    private String memberpass;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10,11}$", message = "전화번호는 10~11자리 숫자만 입력해주세요.")
    private String memberphone;

    @NotBlank
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일은 book@book.com 형식으로 입력해주세요.")
    private String memberemail;

    public String getMemberpass() {
        return memberpass;
    }
}
