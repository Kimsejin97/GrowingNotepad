package blog.domain.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
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
