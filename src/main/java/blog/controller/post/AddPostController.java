package blog.controller.post;

import blog.config.argumentresolver.Login;
import blog.domain.model.Member;
import blog.domain.model.Post;
import blog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AddPostController {

    private final PostService postService;

    @GetMapping("/blog/add")
    public String addPostOpen(@ModelAttribute("post") Post post){
        return "post/addPost";
    }

    @PostMapping("/blog/add")
    public String addPost(@ModelAttribute("post") Post post,
                          @Login Member loginMember,
                          RedirectAttributes redirectAttributes) {
        Post savePost = postService.save(post,loginMember.getName());
        redirectAttributes.addAttribute("postId", savePost.getId());
        return "redirect:/blog/post/{postId}";
    }
}
