package blog.domain.repository;

import blog.domain.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 메모리환경에서 사용한 Post레파지토리 코드
 */
//@Repository
//@RequiredArgsConstructor
//public class PostRepository {
//
//    private static final Map<Long, Post> store = new ConcurrentHashMap<>();
//    private static long sequence = 0L;
//
//    //save
//    public Post save(Post post){
//        post.setId(++sequence);
//        post.setPostTime(LocalDateTime.now());
//        store.put(post.getId(), post);
//        return post;
//    }
//
//    //findById
//    public Post findById(Long id){
//        return store.get(id);
//    }
//
//    //findAll
//    public List<Post> findAll(){
//        return new ArrayList<>(store.values());
//    }
//
//    //update
//    public void update(Long postId, Post Param){
//        Post findPost = findById(postId);
//        findPost.setTitle(Param.getTitle());
//        findPost.setContext(Param.getContext());
//        findPost.setPostTime(LocalDateTime.now());
//    }
//
//    //delete
//    public void deleteById(Long id) { store.remove(id); }
//
//    //clear
//    public void clear(){
//        store.clear();
//    }
//}
