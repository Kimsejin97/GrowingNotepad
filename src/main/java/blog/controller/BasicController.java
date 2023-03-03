package blog.controller;

import blog.domain.Post;
import blog.Repository.PostRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BasicController {
    private final PostRepository postRepository;

    @GetMapping("/blog/home")
    public String posts(Model model){
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "home";
    }

    @GetMapping("/blog/post/{postId}")
    public String post(@PathVariable Long postId, Model model){
        Post post = postRepository.findById(postId);
        model.addAttribute("post",post);
        return "post/post";
    }

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

    @PostConstruct
    public void init(){
        postRepository.save(new Post("새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));
        postRepository.save(new Post("새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));
        postRepository.save(new Post("새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));
        postRepository.save(new Post("새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));

    }
}
