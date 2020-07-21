package edge.of.luck.classes;

import edge.of.luck.entities.User;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.util.Properties;

public class LogicManager {
    public UsersHelper usersHelper;
    public GameLogic gameLogic;
    public Properties properties;
    public final Logger log = LoggerContext.getContext().getLogger("LogicManager");

    public LogicManager(Properties properties) {
        this.properties = properties;
        usersHelper = new UsersHelper(this);
        gameLogic = new GameLogic(this);

    }

    public User getUserByName(String name) {
        User user = usersHelper.getOrCreateUser(name);
        gameLogic.setActiveUser(user);
        log.info(String.format("User %s login", name));
        return user;
    }
}
