package blog.domain.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class UpdatePostDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private LocalDateTime postTime;

    public UpdatePostDto(){}

    public UpdatePostDto(String title, String content, LocalDateTime postTime) {
        this.title = title;
        this.content = content;
        this.postTime = postTime;
    }
}
