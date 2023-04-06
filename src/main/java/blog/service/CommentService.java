package blog.service;

import blog.domain.model.Comment;
import blog.domain.repository.CommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentService {

    private final CommentMapper commentMapper;

    public Comment save(Comment comment, Long postId, String writer) {
        comment.setPostId(postId);
        comment.setCreatedDate(LocalDateTime.now());
        comment.setWriter(writer);
        if (commentMapper.save(comment) == 1) {
            return comment;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public List<Comment> findByPostId(Long postId) {
        return commentMapper.findByPostId(postId);
    }

    public Comment findByCommentId(Long commentId) {
        return commentMapper.findByCommentId(commentId);
    }

    public void deleteByPostId(Long postId) {
        if (commentMapper.findByPostId(postId).stream().count() >= 1) {
            commentMapper.deleteByPostId(postId);
        } else {
            log.info("requested to delete Post postId - {} comment. but, no such comment", postId);
        }
    }

    public void deleteByCommentId(Long commentId) {
            commentMapper.deleteByCommentId(commentId);
    }

    public List<Comment> findCommentsByPostIdWithPagination(Long postId, int page, int size) {
        int start = (page - 1) * size;
        return commentMapper.findByPostIdPage(postId, start, size);
    }

    public int countCommentsByPostId(Long postId) {
        return commentMapper.countByPostId(postId);
    }
}
