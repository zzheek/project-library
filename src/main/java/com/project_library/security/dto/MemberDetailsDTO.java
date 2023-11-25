package com.project_library.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@ToString
public class MemberDetailsDTO extends User {

    private String memberid;    // 회원 아이디
    private String membername;  // 회원 이름
    private String memberphone; // 회원 전화번호
    private String memberemail; // 회원 이메일


    public MemberDetailsDTO(String username,
                            String password,
                            String membername,
                            String memberemail,
                            String memberphone) {
        super(username,password,new ArrayList<>());


        this.memberid = username;
        this.membername = membername;
        this.memberemail = memberemail;
        this.memberphone = memberphone;

    }
}
