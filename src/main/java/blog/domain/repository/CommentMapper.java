package blog.domain.repository;

import blog.domain.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {

    int save(Comment comment);
    List<Comment> findByPostId(Long postId);
    Comment findByCommentId(Long commentId);
    int deleteByPostId(Long postId);
    int deleteByCommentId(Long commentId);

}
