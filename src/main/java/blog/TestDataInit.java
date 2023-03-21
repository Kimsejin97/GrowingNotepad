package blog;

import blog.domain.model.Member;
import blog.domain.repository.MemberRepository;
import blog.domain.model.Post;
import blog.domain.repository.PostRepository;
import jakarta.annotation.PreDestroy;
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
    @PreDestroy
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