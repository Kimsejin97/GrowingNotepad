package blog;

import blog.domain.repository.MemberRepository;
import blog.domain.service.PostService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@MapperScan("blog.domain.repository")
public class GrowingNotepadApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrowingNotepadApplication.class, args);
	}
}
