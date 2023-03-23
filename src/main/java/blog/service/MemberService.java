package blog.service;

import blog.domain.model.Member;
import blog.domain.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {

    private final MemberMapper memberMapper;

    public Member save(Member member) {
        if (memberMapper.save(member) == 1) {
            return member;
        } else {
            throw new IllegalArgumentException("회원가입 실패");
        }
    }

    public Member findById(Long id) {
        return memberMapper.findById(id);
    }

    public Optional<Member> findByLoginEmail(String email) {
        return findAll().stream()
                .filter(m -> m.getEmail().equals(email))
                .findFirst();
    }

    public List<Member> findAll() {
        return memberMapper.findAll();
    }

}
