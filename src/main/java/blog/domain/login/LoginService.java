package blog.domain.login;

import blog.domain.member.Member;
import blog.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    //return null 로그인실패
    public Member login(String email, String password) {
        return memberRepository.findByLoginEmail(email)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
