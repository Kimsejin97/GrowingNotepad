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

    public Comment save(Comment comment, String writer) {
        comment.setCreateDate(LocalDateTime.now());
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

}
