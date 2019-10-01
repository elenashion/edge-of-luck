package main.java;

import main.java.entities.User;
import org.apache.log4j.Logger;

import java.util.Properties;

public class LogicManager {
    public CacheManager cacheManager;
    public GameLogic gameLogic;
    public Properties properties;
    public final Logger log = Logger.getLogger(LogicManager.class);

    public LogicManager(Properties properties) {
        this.properties = properties;
        cacheManager = new CacheManager(this);
        gameLogic = new GameLogic(this);

    }

    private User createNewUser(String name) {
        User user = new User(name);
        cacheManager.putUserByName(user);
        return user;
    }

    public User getOrCreateUser(String name) {
        User user;
        user = cacheManager.getUserByName(name);
        if (user == null)
            user = createNewUser(name);
        gameLogic.setActiveUser(user);
        log.info(String.format("User %s login", name));
        return user;
    }
}
