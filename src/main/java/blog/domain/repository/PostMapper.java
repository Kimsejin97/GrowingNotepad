package blog.domain.repository;

import blog.domain.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PostMapper {

    Post save(Post post);
    void update(@Param("id") Long id, @Param("updateParam") Post updateParam);
    void deleteById(Long id);
    void clear();
    Post findById(Long id);
    List<Post> findAll();

}
