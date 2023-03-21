package blog;

import blog.domain.model.Member;
import blog.domain.model.Post;
import blog.domain.repository.MemberRepository;
import blog.domain.service.PostService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TestDataInit {

//    private final PostService postService;
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

//        postService.save(new Post("테스터","새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));
//        postService.save(new Post("테스터","새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));
//        postService.save(new Post("테스터","새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));
//        postService.save(new Post("테스터","새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));

    }

}