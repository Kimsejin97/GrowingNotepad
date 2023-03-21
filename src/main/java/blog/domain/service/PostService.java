package blog.domain.service;

import blog.domain.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post save(Post post);
    void update(long id, Post updateParam);
    void deleteById(long id);
    void clear();
    Post findById(long id);
    List<Post> findAll();
}
