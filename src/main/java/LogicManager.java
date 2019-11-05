package main.java;

import main.java.entities.User;
import org.apache.log4j.Logger;

import java.util.Properties;

public class LogicManager {
    public UsersHelper usersHelper;
    public GameLogic gameLogic;
    public Properties properties;
    public final Logger log = Logger.getLogger(LogicManager.class);

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
