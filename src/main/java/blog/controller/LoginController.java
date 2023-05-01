package blog.controller;

import blog.config.argumentresolver.SessionConst;
import blog.domain.model.Member;
import blog.domain.model.dto.LoginMemberDto;
import blog.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginMemberDto form) {
        return "login/signIn";
    }

    @GetMapping("/join")
    public String joinForm(@ModelAttribute("member") Member member) {
        return "login/signUp";
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute("member") Member member,
                       BindingResult bindingResult,
                       HttpServletRequest request) {
        log.info("join 로직");
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "login/signUp";
        }
        // 중복된 이름인지 확인
        if (memberService.findByName(member.getName()) != null) {
            bindingResult.rejectValue("name", "duplicate.name");
            return "login/signUp";
        }
        // 중복된 이메일인지 확인
        if (memberService.findByEmail(member.getEmail()) != null) {
            bindingResult.rejectValue("email", "duplicate.email");
            return "login/signUp";
        }
        memberService.save(member);
        Member loginMember = memberService.login(member.getEmail(), member.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        return "redirect:/blog/home";
    }
}
