package blog.domain.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @ToString
public class Member {

    private Long id;

    @NotEmpty
    private String name; //사용자 이름
    @NotEmpty
    private String email; //로그인 이메일
    @NotEmpty
    @Size(min = 8, message = "8자리 이상 문자를 입력해주세요.")
    private String password; //로그인 비밀번호
}
