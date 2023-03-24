package blog.domain.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestPostDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public RequestPostDto(){}

    public RequestPostDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
