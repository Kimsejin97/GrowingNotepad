package blog.service;

import blog.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberService memberService;

    //return null 로그인실패
    public Member login(String email, String password) {
        return memberService.findByLoginEmail(email)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }


}
