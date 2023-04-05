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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/blog/post/{postId}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

//    @GetMapping
//    public String showComment(@PathVariable("postId") Long postId,
//                              Model model) {
//        List<Comment> comments = commentService.findByPostId(postId);
//        model.addAttribute("comments", comments);
//        return "redirect:/blog/post/"+postId;
//    }

    @PostMapping("/save")
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

    @PostMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable("postId") Long postId,
                                @PathVariable("commentId") Long commentId,
                                @Login Member loginMember) {
        Comment comment = commentService.findByCommentId(commentId);
        if (loginMember.getName().equals(comment.getWriter())) {
            commentService.deleteByCommentId(commentId);
        }
        return "redirect:/blog/post/"+postId;
    }
}
