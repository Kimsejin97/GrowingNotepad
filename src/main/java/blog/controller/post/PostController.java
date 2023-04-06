package blog.controller.post;

import blog.config.argumentresolver.Login;
import blog.domain.model.Comment;
import blog.domain.model.Member;
import blog.domain.model.Post;
import blog.service.CommentService;
import blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/blog/post/{postId}")
    public String post(@PathVariable("postId") Long postId,
                       Model model,
                       @Login Member loginMember,
                       @RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "5") int size){
        //post
        Post post = postService.findById(postId);
        if (loginMember.getName().equals(post.getWriter())) {
            model.addAttribute("edit", true);
        }
        //comment
        List<Comment> comments = commentService.findCommentsByPostIdWithPagination(postId,page,size);
        int totalPosts = commentService.countCommentsByPostId(postId);
        int totalPages = (int) Math.ceil((double) totalPosts / size);
        comments.stream()
                .forEach(comment -> {
                    comment.setCheckWriter(comment.getWriter().equals(loginMember.getName()));
                });

        model.addAttribute("post",post);
        model.addAttribute("comments", comments);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("page", page);
        model.addAttribute("postId",postId);
        return "post/post";
    }

}
