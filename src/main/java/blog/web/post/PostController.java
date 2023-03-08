package blog.web.post;

import blog.domain.post.Post;
import blog.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;

    //상세
    @GetMapping("/blog/post/{postId}")
    public String post(@PathVariable Long postId, Model model){
        Post post = postRepository.findById(postId);
        model.addAttribute("post",post);
        return "post/post";
    }

    //저장
    @GetMapping("/blog/add")
    public String addPostOpen(){
        return "post/addPost";
    }

    @PostMapping("/blog/add")
    public String addPost(@ModelAttribute Post post, RedirectAttributes redirectAttributes) {
        Post savePost = postRepository.save(post);
        redirectAttributes.addAttribute("postId", savePost.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/blog/post/{postId}";
    }

    //수정
    @GetMapping("/blog/post/{postId}/edit")
    public String editPostOpen(@PathVariable Long postId,Model model){
        Post post = postRepository.findById(postId);
        model.addAttribute("post",post);
        return "post/editPost";
    }

    @PostMapping("/blog/post/{postId}/edit")
    public String editPost(@PathVariable Long postId,
                           @ModelAttribute Post post){
        postRepository.update(postId,post);
        return "redirect:/blog/post/{postId}";
    }
}
