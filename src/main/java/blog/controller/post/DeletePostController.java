package blog.controller.post;

import blog.service.CommentService;
import blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DeletePostController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/blog/post/{postId}/delete")
    public String deletePost(@PathVariable Long postId, Model model){
        postService.deleteById(postId);
        commentService.deleteByPostId(postId);
//        model.addAttribute("deleteStatus", true);
        return "redirect:/blog/home";
    }
}
