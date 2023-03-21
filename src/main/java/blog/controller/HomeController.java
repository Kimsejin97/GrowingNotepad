package blog.controller;

import blog.domain.model.Member;
import blog.domain.model.Post;
import blog.config.argumentresolver.Login;
import blog.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping("/blog/home")
    public String posts(@Login Member loginMember, Model model) {
        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "redirect:/blog/visitorHome";
        }
        //세션이 유지되면 로그인으로 이동
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("member", loginMember);
        return "home";
    }

    @GetMapping("/blog/visitorHome")
    public String visitor(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "visitorHome";
    }
}
