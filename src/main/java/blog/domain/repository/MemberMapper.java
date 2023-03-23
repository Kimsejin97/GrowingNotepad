package blog.domain.repository;

import blog.domain.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MemberMapper {

    int save(Member member);
    Member findById(Long id);
    Member findByLoginEmail(String email);
    List<Member> findAll();

}
