package edge.of.luck;

import edge.of.luck.classes.UsersHelper;
import edge.of.luck.classes.GameLogic;
import edge.of.luck.configuration.PointsProperties;

public class AbstractTest {
    protected UsersHelper usersHelper;
    protected GameLogic gameLogic;

    public AbstractTest() {
        usersHelper = new UsersHelper();
        gameLogic = new GameLogic(new PointsProperties());
    }
}
