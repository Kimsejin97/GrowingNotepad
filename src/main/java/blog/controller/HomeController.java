package blog.controller;

import blog.config.argumentresolver.Login;
import blog.domain.model.Member;
import blog.domain.model.Post;
import blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping("/blog/home")
    public String posts(Model model,
                        @RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "5") int size,
                        @RequestParam(required = false) String searchField,
                        @RequestParam(required = false) String searchTerm) {

        List<Post> posts;
        int totalPosts;
        if (searchField != null && searchTerm != null) {
            //검색어가 있는경우
            log.info("검색어 있음");
            posts = postService.findPostsByKeyword(searchField, searchTerm, page, size);
            totalPosts = postService.countPostsByKeyword(searchField, searchTerm);
        } else {
            //검색어가 없는경우
            log.info("검색어 없음");
            posts = postService.findAll(page, size);
            totalPosts = postService.countAllPosts();
        }

        int totalPages = (int) Math.ceil((double) totalPosts / size);

        model.addAttribute("posts", posts);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("page", page);
        model.addAttribute("searchField", searchField);
        model.addAttribute("searchTerm", searchTerm);
        return "home";
    }
}
