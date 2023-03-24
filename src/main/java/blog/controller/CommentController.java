package blog.controller;

import blog.config.argumentresolver.Login;
import blog.domain.model.Comment;
import blog.domain.model.Member;
import blog.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/blog/post/{postId}/comments")
    public String getComments(@PathVariable("postId") Long postId, Model model) {
        List<Comment> comments = commentService.findByPostId(postId);
        model.addAttribute("comments", comments);
        return "post/post";
    }

    @PostMapping("/blog/post/{postId}")
    public String saveComment(@Valid Comment comment,
                              BindingResult bindingResult,
                              @Login Member loginMember,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "post/post";
        }
        Comment saveComment = commentService.save(comment, loginMember.getName());
        redirectAttributes.addAttribute("postId", saveComment.getPostId());
        return "redirect:/blog/post/{postId}";
    }
}
