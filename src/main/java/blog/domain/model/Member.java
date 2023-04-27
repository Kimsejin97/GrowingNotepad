package blog.domain.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter @Setter @ToString
public class Member {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 8)
    private String password;

    private String authority;
    private Boolean enabled;
}
