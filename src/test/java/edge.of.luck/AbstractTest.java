package edge.of.luck;

import edge.of.luck.classes.UsersHelper;
import edge.of.luck.classes.GameLogic;
import edge.of.luck.classes.LogicManager;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class AbstractTest {
    protected UsersHelper usersHelper;
    protected LogicManager logicManager;
    protected GameLogic gameLogic;
    protected Connection connection;

    public AbstractTest() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/test/resources/test.properties"));
            logicManager = new LogicManager(properties);
            usersHelper = new UsersHelper(logicManager);
            gameLogic = logicManager.gameLogic;

            String url = properties.getProperty("hibernate.db.url");
            String user = properties.getProperty("hibernate.db.user");
            String password = properties.getProperty("hibernate.db.password");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {}
    }
}
