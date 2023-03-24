package blog.controller;

import blog.config.argumentresolver.Login;
import blog.domain.model.Comment;
import blog.domain.model.Member;
import blog.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/blog/post/{postId}")
    public String saveComment(@PathVariable("postId") Long postId,
                              @Valid Comment comment,
                              BindingResult bindingResult,
                              @Login Member loginMember,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "post/post";
        }
        Comment saveComment = commentService.save(comment, postId, loginMember.getName());
        Long savePostId = saveComment.getPostId();
        redirectAttributes.addAttribute("postId", savePostId);
        return "redirect:/blog/post/"+savePostId;
    }
}
