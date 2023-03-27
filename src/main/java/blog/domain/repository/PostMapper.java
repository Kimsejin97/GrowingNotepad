package blog.domain.repository;

import blog.domain.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PostMapper {

    int save(Post post);
    int update(@Param("id") Long id, @Param("updateParam") Post updateParam);
    int deleteById(Long id);
    int clear();
    Post findById(Long id);
    List<Post> findAll();
    List<Post> findAllByPagination(@Param("start") int start, @Param("count") int count);
    int countAllPosts();

}
