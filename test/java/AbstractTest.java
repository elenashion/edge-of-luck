package java;

import main.java.CacheManager;
import main.java.GameLogic;
import main.java.LogicManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AbstractTest {
    protected CacheManager cacheManager;
    protected LogicManager logicManager;
    protected GameLogic gameLogic;

    public AbstractTest() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/test/resources/test.properties"));
            logicManager = new LogicManager(properties);
            cacheManager = new CacheManager(logicManager);
            gameLogic = logicManager.gameLogic;
        } catch (IOException e) {}
    }
}
