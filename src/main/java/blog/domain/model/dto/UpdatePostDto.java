package blog.domain.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdatePostDto {

    @NotBlank
    private String title;

    @NotBlank
    private String context;

    private LocalDateTime postTime;

    public UpdatePostDto(){}

    public UpdatePostDto(String title, String context, LocalDateTime postTime) {
        this.title = title;
        this.context = context;
        this.postTime = postTime;
    }
}
