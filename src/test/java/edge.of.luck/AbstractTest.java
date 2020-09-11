package edge.of.luck;

import edge.of.luck.classes.UsersHelper;
import edge.of.luck.classes.GameLogic;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractTest {
    protected UsersHelper usersHelper;
    protected GameLogic gameLogic;

    public AbstractTest() {
        usersHelper = new UsersHelper();
        gameLogic = new GameLogic();
    }
}
