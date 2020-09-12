package edge.of.luck.configuration;

import edge.of.luck.classes.GameLogic;
import edge.of.luck.classes.UsersHelper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@Configuration
@EnableConfigurationProperties(PointsProperties.class)
public class BeanConfiguration {
    private Environment env;
    private PointsProperties points;

    BeanConfiguration(Environment env, PointsProperties points) {
        this.env = env;
        this.points = points;
    }

    @Bean
    @Primary
    public UsersHelper usersHelper() {
        return new UsersHelper();
    }

    @Bean
    @Primary
    public GameLogic gameLogic() {
        return new GameLogic(points);
    }

    @Bean
    @Primary
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }
}
