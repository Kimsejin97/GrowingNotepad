package blog.domain.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Post {

    private Long id;

    private String writer;

    @NotBlank
    private String title;

    @NotBlank
    private String context;

    private LocalDateTime postTime;

    public Post(){}

    public Post(String title, String context, LocalDateTime postTime) {
        this.writer = writer;
        this.title = title;
        this.context = context;
        this.postTime = postTime;
    }
}
