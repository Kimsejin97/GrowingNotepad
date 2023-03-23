package blog.controller;

import blog.config.etc.CurrentSessionLogin;
import blog.domain.model.Member;
import blog.domain.model.Post;
import blog.config.argumentresolver.Login;
import blog.domain.model.dto.UpdatePostDto;
import blog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/blog/post/{postId}")
    public String post(@PathVariable Long postId, Model model, @Login Member loginMember){
        Post post = postService.findById(postId);
        if (loginMember.getName().equals(post.getWriter())) {
            model.addAttribute("edit", true);
        }
        model.addAttribute("post",post);
        return "post/post";
    }

    @GetMapping("/blog/add")
    public String addPostOpen(){
        return "post/addPost";
    }

    @PostMapping("/blog/add")
    public String addPost(@Valid @ModelAttribute("post") Post post,
                          BindingResult bindingResult,
                          @Login Member loginMember,
                          RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "post/addPost";
        }
        Post savePost = postService.save(post,loginMember.getName());
        redirectAttributes.addAttribute("postId", savePost.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/blog/post/{postId}";
    }

    @GetMapping("/blog/post/{postId}/edit")
    public String editPostOpen(@PathVariable Long postId,Model model){
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

    @GetMapping("/blog/post/{postId}/delete")
    public String deletePost(@PathVariable Long postId,Model model){
        postService.deleteById(postId);
        model.addAttribute("deleteStatus", true);
        return "redirect:/blog/home";
    }
}
