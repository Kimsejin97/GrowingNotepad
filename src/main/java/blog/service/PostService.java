package blog.service;

import blog.config.CurrentSessionLogin;
import blog.domain.model.Member;
import blog.domain.model.Post;
import blog.domain.model.dto.UpdatePostDto;
import blog.domain.repository.PostMapper;
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
public class PostService {

    private final PostMapper postMapper;
    private final CurrentSessionLogin currentSessionLogin;

    public Post save(Post post) {
        if (postMapper.save(post) == 1) {
            Member sessionLogin = (Member) currentSessionLogin.sessionLogin();
            String writer = sessionLogin.getName();
            post.setWriter(writer);
            postMapper.save(post);
            return post;
        } else {
           throw new IllegalArgumentException();
        }
    }

    public void update(long id, UpdatePostDto updateParam) {
        Post post = postMapper.findById(id);
        post.setTitle(updateParam.getTitle());
        post.setContext(updateParam.getContext());
        post.setPostTime(LocalDateTime.now());
        if (postMapper.update(id, post) != 1) {
            throw new IllegalArgumentException();
        }
    }

    public void deleteById(long id) {
        postMapper.deleteById(id);
    }

    public void clear() {
        postMapper.clear();
    }

    public Post findById(long id) {
        return postMapper.findById(id);
    }

    public List<Post> findAll() {
        return postMapper.findAll();
    }
}
