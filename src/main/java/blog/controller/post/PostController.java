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
                       @Login Member loginMember){
        Post post = postService.findById(postId);
        List<Comment> comments = commentService.findByPostId(postId);
        if (loginMember.getName().equals(post.getWriter())) {
            model.addAttribute("edit", true);
        }
        model.addAttribute("post",post);
        model.addAttribute("comments", comments);
        model.addAttribute("postId",postId);
        return "post/post";
    }

}
