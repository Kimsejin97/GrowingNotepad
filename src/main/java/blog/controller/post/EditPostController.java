package blog.controller.post;

import blog.domain.model.Post;
import blog.domain.model.dto.UpdatePostDto;
import blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EditPostController {

    private final PostService postService;

    @GetMapping("/blog/post/{postId}/edit")
    public String editPostOpen(@PathVariable Long postId, Model model){
        Post post = postService.findById(postId);
        model.addAttribute("post",post);
        return "post/editPost";
    }

    @PostMapping("/blog/post/{postId}/edit")
    public String editPost(@PathVariable Long postId,
                           @ModelAttribute UpdatePostDto post){
        postService.update(postId,post);
        return "redirect:/blog/post/{postId}";
    }
}
