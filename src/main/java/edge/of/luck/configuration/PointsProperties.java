package edge.of.luck.configuration;

import edge.of.luck.entities.GameResultPoints;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("points")
public class PointsProperties {
    private String some;
    private GameResultPoints user;
    private GameResultPoints enemy;

}
