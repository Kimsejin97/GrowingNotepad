package blog.domain.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestPostDto {

    @NotBlank
    private String title;

    @NotBlank
    private String context;

    public RequestPostDto(){}

    public RequestPostDto(String title, String context) {
        this.title = title;
        this.context = context;
    }
}
