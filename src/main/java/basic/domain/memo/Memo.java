package basic.domain.memo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter @Setter
public class Memo {

    private Long id;

    private String title;

    private String text;

    private LocalDateTime postTime;

    public Memo(){}

    public Memo(String title, String text, LocalDateTime postTime) {
        this.title = title;
        this.text = text;
        this.postTime = postTime;
    }
}
