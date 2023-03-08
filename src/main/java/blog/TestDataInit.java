package blog;

import blog.domain.member.Member;
import blog.domain.member.MemberRepository;
import blog.domain.post.Post;
import blog.domain.post.PostRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){

        Member member = new Member();
        member.setEmail("test@test.com");
        member.setPassword("test123123");
        member.setName("테스터");
        memberRepository.save(member);

        postRepository.save(new Post("새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));
        postRepository.save(new Post("새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));
        postRepository.save(new Post("새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));
        postRepository.save(new Post("새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));

    }

}