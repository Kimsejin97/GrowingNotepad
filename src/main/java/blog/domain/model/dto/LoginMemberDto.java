package blog.domain.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginMemberDto {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
