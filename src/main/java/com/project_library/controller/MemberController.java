package com.project_library.controller;

import com.project_library.dto.MemberDTO;
import com.project_library.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public void loginGet() {

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext()
                .getAuthentication());

        return "redirect:/member/login";

    }

    @GetMapping("/join")
    public void joinGet(Model model) {

        model.addAttribute("memberDTO", new MemberDTO());
    }

    @PostMapping("/join")
    public String joinPost(@Valid MemberDTO memberDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("memberDTO", memberDTO);

            Map<String, String> validatorResult = memberService.validateHandling(bindingResult);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "/member/join";
        }

        memberService.join(memberDTO);
        model.addAttribute("memberDTO", memberDTO);


        return "redirect:/member/login";
    }




}
