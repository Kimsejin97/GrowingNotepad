package blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public CurrentSessionLogin sessionLogin() {
        return new CurrentSessionLogin();
    }

}
