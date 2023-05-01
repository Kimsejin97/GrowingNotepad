package blog.security;

import blog.config.argumentresolver.SessionConst;
import blog.domain.model.Member;
import blog.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("CustomUserDetailsService.loadUserByUsername()");

        Member member = memberService.findByLoginEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("userName not found"));

        RequestContextHolder.getRequestAttributes().setAttribute(
                SessionConst.LOGIN_MEMBER, member, RequestAttributes.SCOPE_SESSION);

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(member.getAuthority()));


        return new User(member.getEmail(), member.getPassword(), member.getEnabled(),
                true, true, true, authorityList);
    }
}
