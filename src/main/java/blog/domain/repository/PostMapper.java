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
    Post findById(Long id);
    List<Post> findAll(@Param("start") int start, @Param("count") int count);
    List<Post> findPostsByKeyword(
            @Param("searchField") String searchField,
            @Param("searchTerm") String searchTerm,
            @Param("start") int start,
            @Param("count") int count);
    int countAllPosts();
    int countPostsByKeyword(@Param("searchField") String searchField, @Param("searchTerm") String searchTerm);
}
