package edge.of.luck.configuration;

import edge.of.luck.classes.GameLogic;
import edge.of.luck.classes.UsersHelper;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BeanConfiguration {
    BeanConfiguration() {
    }

    @Bean
    @Primary
    public UsersHelper usersHelper() {
        return new UsersHelper();
    }

    @Bean
    @Primary
    public GameLogic gameLogic() {
        return new GameLogic();
    }

    @Bean
    @Primary
    public ServletWebServerFactory factory() {
        return initializers -> null;
    }
}
