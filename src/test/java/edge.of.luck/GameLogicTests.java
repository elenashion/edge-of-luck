package edge.of.luck;

import edge.of.luck.entities.ComputerPlayer;
import edge.of.luck.entities.enums.GameChoice;
import edge.of.luck.entities.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameLogicTests extends AbstractTest {

    private User user = new User("Kaoru");

    @Test
    public void notSuccessEnemiesCreateTest() {
        gameLogic.createEnemies(0);
        assertEquals(0, gameLogic.getEnemiesSize());

        gameLogic.createEnemies(5);
        assertEquals(0, gameLogic.getEnemiesSize());

        gameLogic.createEnemies(-2);
        assertEquals(0, gameLogic.getEnemiesSize());
    }

    @Test
    public void successCreateEnemiesTest() {
        gameLogic.createEnemies(1);
        assertEquals(1, gameLogic.getEnemiesSize());

        ComputerPlayer enemy1 = gameLogic.getEnemyByNumber(0);
        assertNotNull(enemy1);
        assertNotNull(enemy1.getName());
        assertEquals(100, enemy1.getPoints());
        assertEquals(0, enemy1.getNumber());
    }

    @Test
    public void makeSameDecisionsTest() {
        gameLogic.createEnemies(3);
        ComputerPlayer enemy1 = gameLogic.getEnemyByNumber(1);
        ComputerPlayer enemy2 = gameLogic.getEnemyByNumber(2);

        for (int i = 0; i < 100; i++)
            assertEquals(GameChoice.EVEN, enemy1.makeADecision(i));

        for (int i = 0; i < 100; i++)
            assertEquals(GameChoice.ODD, enemy2.makeADecision(i));

    }

    @Test
    public void activeUserTest() {
        assertNull(gameLogic.getActiveUser());
        gameLogic.setActiveUser(user);
        assertNotNull(gameLogic.getActiveUser());
    }


}
