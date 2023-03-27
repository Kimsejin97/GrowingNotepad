package blog;

import blog.domain.model.Member;
import blog.domain.model.Post;
import blog.domain.repository.MemberMapper;
import blog.domain.repository.PostMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberMapper memberMapper;
    private final PostMapper postMapper;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){

        Member member = new Member();
        member.setEmail("test@test.com");
        member.setPassword("test123123");
        member.setName("테스터");
        memberMapper.save(member);

        postMapper.save(new Post("테스터","새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));
        postMapper.save(new Post("테스터","새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));
        postMapper.save(new Post("테스터","새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));
        postMapper.save(new Post("테스터","새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));
        postMapper.save(new Post("테스터","새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));
        postMapper.save(new Post("테스터","새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));
        postMapper.save(new Post("테스터","새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));
        postMapper.save(new Post("테스터","새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));

    }

}