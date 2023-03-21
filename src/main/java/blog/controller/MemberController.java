package blog.controller;

import blog.domain.service.LoginService;
import blog.domain.model.Member;
import blog.domain.repository.MemberRepository;
import blog.constants.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final LoginService loginService;

    //회원가입
    @GetMapping("/join")
    public String joinForm(@ModelAttribute("member") Member member) {
        return "login/signUp";
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute("member") Member member,
                       BindingResult bindingResult,
                       HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/signUp";
        }
        memberRepository.save(member);
        Member loginMember = loginService.login(member.getEmail(), member.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        return "redirect:/blog/home";
    }
}
