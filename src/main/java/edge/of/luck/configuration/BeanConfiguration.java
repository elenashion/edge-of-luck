package edge.of.luck.configuration;

import edge.of.luck.classes.GameLogic;
import edge.of.luck.classes.UsersHelper;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackageClasses = BeanConfiguration.class)
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
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }
}
