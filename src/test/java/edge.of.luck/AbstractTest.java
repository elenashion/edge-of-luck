package edge.of.luck;

import edge.of.luck.classes.UsersHelper;
import edge.of.luck.classes.GameLogic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AbstractTest {
    protected UsersHelper usersHelper;
    protected GameLogic gameLogic;

    public AbstractTest() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/test/resources/test.properties"));
            usersHelper = new UsersHelper(properties);
            gameLogic = new GameLogic(properties);
        } catch (IOException e) {}
    }
}
