package blog.domain.service;

import blog.domain.model.Post;
import blog.domain.repository.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService{

    private final PostMapper postMapper;

    @Override
    public Post save(Post post) {
        return postMapper.save(post);
    }

    @Override
    public void update(long id, Post updateParam) {
        Post post = postMapper.findById(id);
        post.setTitle(updateParam.getTitle());
        post.setContext(updateParam.getContext());
        post.setPostTime(LocalDateTime.now());
        postMapper.update(id, post);
    }

    @Override
    public void deleteById(long id) {
        postMapper.deleteById(id);
    }

    @Override
    public void clear() {
        postMapper.clear();
    }

    @Override
    public Post findById(long id) {
        return postMapper.findById(id);
    }

    @Override
    public List<Post> findAll() {
        return postMapper.findAll();
    }
}
