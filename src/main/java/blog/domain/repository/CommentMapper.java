package blog.domain.repository;

import blog.domain.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {

    int save(@Param("comment") Comment comment);
    List<Comment> findByPostId(@Param("postId") Long postId);

}
