package blog.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class Comment {

    private Long id;

    private Long postId;

    private String writer;

    @NotBlank
    private String content;

    private LocalDateTime createdDate;

    public Comment(){}

    public Comment(Long id, Long postId, String writer, String content, LocalDateTime createdDate) {
        this.id = id;
        this.postId = postId;
        this.writer = writer;
        this.content = content;
        this.createdDate = createdDate;
    }
}
