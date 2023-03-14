package blog.web.post;

import blog.domain.member.Member;
import blog.domain.post.Post;
import blog.domain.post.PostRepository;
import blog.web.login.argumentresolver.Login;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;

    //상세
    @GetMapping("/blog/post/{postId}")
    public String post(@PathVariable Long postId, Model model, @Login Member loginMember){
        Post post = postRepository.findById(postId);
        if (loginMember.getName() == post.getWriter()) {
            model.addAttribute("edit", true);
        }
        model.addAttribute("post",post);
        return "post/post";
    }

    //저장
    @GetMapping("/blog/add")
    public String addPostOpen(){
        return "post/addPost";
    }

    @PostMapping("/blog/add")
    public String addPost(@Valid @ModelAttribute("post") Post post,
                          @Login Member loginMember,
                          RedirectAttributes redirectAttributes) {

        String writer = loginMember.getName();
        post.setWriter(writer);

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

    //삭제
    @GetMapping("/blog/post/{postId}/delete")
    public String deletePost(@PathVariable Long postId,Model model){
        postRepository.deleteById(postId);
        model.addAttribute("deleteStatus", true);
        return "redirect:/blog/home";
    }
}
