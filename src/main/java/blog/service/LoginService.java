package blog.service;

import blog.domain.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    //return null 로그인실패
    public Member login(String email, String password) {
        return memberService.findByLoginEmail(email)
//                .filter(m -> m.getPassword().equals(password))
                .filter(m -> passwordEncoder.matches(password, m.getPassword()))
                .orElse(null);
    }


}
