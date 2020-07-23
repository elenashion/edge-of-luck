package edge.of.luck.classes;

import edge.of.luck.entities.ComputerPlayer;
import edge.of.luck.entities.enums.GameChoice;
import edge.of.luck.entities.enums.GameResult;
import edge.of.luck.entities.User;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


public class GameLogic {
    private final Logger log = LoggerContext.getContext().getLogger("GameLogic");
    private int round = 0;

    private int firstNumber;
    private int secondNumber;
    private GameChoice roundResult;

    private final int MAX_ENEMIES_SIZE;
    private User activeUser;
    private List<ComputerPlayer> activeEnemies = new ArrayList<>();

    private final int userWinEnemyLoseUserPoints;
    private final int userWinEnemyWinUserPoints;
    private final int userLoseEnemyWinUserPoints;
    private final int userLoseEnemyLoseUserPoints;
    private final int userWinEnemyLoseEnemyPoints;
    private final int userWinEnemyWinEnemyPoints;
    private final int userLoseEnemyWinEnemyPoints;
    private final int userLoseEnemyLoseEnemyPoints;


    public GameLogic(LogicManager logicManager) {
        MAX_ENEMIES_SIZE = Integer.valueOf(logicManager.properties.getProperty("main.maxEnemiesSize"));
        userWinEnemyLoseUserPoints = Integer.valueOf(logicManager.properties.getProperty("main.user.points.userWinEnemyLose"));
        userWinEnemyWinUserPoints = Integer.valueOf(logicManager.properties.getProperty("main.user.points.userWinEnemyWin"));
        userLoseEnemyWinUserPoints = Integer.valueOf(logicManager.properties.getProperty("main.user.points.userLoseEnemyWin"));
        userLoseEnemyLoseUserPoints = Integer.valueOf(logicManager.properties.getProperty("main.user.points.userLoseEnemyLose"));
        userWinEnemyLoseEnemyPoints = Integer.valueOf(logicManager.properties.getProperty("main.enemy.points.userWinEnemyLose"));
        userWinEnemyWinEnemyPoints = Integer.valueOf(logicManager.properties.getProperty("main.enemy.points.userWinEnemyWin"));
        userLoseEnemyWinEnemyPoints = Integer.valueOf(logicManager.properties.getProperty("main.enemy.points.userLoseEnemyWin"));
        userLoseEnemyLoseEnemyPoints = Integer.valueOf(logicManager.properties.getProperty("main.enemy.points.userLoseEnemyLose"));

    }

    public void createEnemies(int number) {
        if (number <= 0 || number > MAX_ENEMIES_SIZE) {
            log.error("Enemies number is incorrect! Number={}", number);
            return;
        }
        activeEnemies.clear();
        for (int i = 0; i < number; i++) {
            activeEnemies.add(i, new ComputerPlayer(this, i));
        }
        for (int i = 0; i < number; i++) {
            ComputerPlayer enemy = activeEnemies.get(i);
            for (int j = 0; j < number; j++) {
                if (i == j)
                    continue;
                if (enemy.getName().equals(activeEnemies.get(j).getName())){
                    activeEnemies.remove(j);
                    activeEnemies.add(j, new ComputerPlayer(this, j));
                    i = 0;
                    j = 0;
                }
            }
        }
        log.info("Enemies created. Number of enemies={}", number);
    }

    public int getRandom() {
        return Integer.parseInt(new BigDecimal(Math.random() * 100).abs().divide(BigDecimal.ONE, 0, RoundingMode.DOWN).toString());
    }

    public int getFirstNumber() { return firstNumber; }

    public int getSecondNumber() { return secondNumber; }

    public void setNumbers () {
        round++;
        firstNumber = getRandom();
        secondNumber = getRandom();
        roundResult = (firstNumber + secondNumber) % 2 == 1 ? GameChoice.ODD : GameChoice.EVEN;
        log.info("setNumbers. Result created. FirstNumber={}, secondNumber={}, roundNumber={}, roundResult={}, activeUser={}", firstNumber, secondNumber, round, roundResult, activeUser.getName());

        for (ComputerPlayer cp : activeEnemies) {
            GameChoice result = cp.makeADecision(round);
            cp.putResult(round, roundResult == result ? GameResult.WIN : GameResult.LOSE);
        }
        log.info("setNumbers. Enemies make a decision. RoundNumber={}, roundResult={}, enemiesSize={}, activeUser={}", round, roundResult, activeEnemies.size(), activeUser.getName());
    }

    public String getResultStringFromUserDecision(GameChoice choice) {
        activeUser.getDecisions().put(round, choice);
        GameResult playerResult = roundResult == choice ? GameResult.WIN : GameResult.LOSE;
        activeUser.getResults().put(round, playerResult);
        log.info("getResultStringFromUserDecision. PlayerResult={}, activeUser={}", playerResult, activeUser.getName());
        setPoints(playerResult);
        return playerResult.getResult();
    }

    public void resetRound() {
        round = activeUser.getDecisions().size() - 1;
    }

    private void setPoints(GameResult playerResult) {
        boolean player = playerResult == GameResult.WIN;
        int enemies = 0;
        for (ComputerPlayer cp : activeEnemies) {
            int points;
            if (player && cp.getResultByRound(round) == GameResult.WIN) {
                points = userWinEnemyWinEnemyPoints;
                enemies++;
            } else if (player && cp.getResultByRound(round) == GameResult.LOSE) {
                points = userWinEnemyLoseEnemyPoints;
            } else if (!player && cp.getResultByRound(round) == GameResult.WIN) {
                points = userLoseEnemyWinEnemyPoints;
                enemies++;
            } else {
                points = userLoseEnemyLoseEnemyPoints;
            }
            cp.setPoints(points);
            log.info("setPoints. {} choose {}, result={}, set {} points. Round={}, activeUser={}",
                    cp.getName(), cp.getChoiceByRound(round), cp.getResultByRound(round), points, round, activeUser.getName());
        }

        int points = (player && enemies == 0) ? userWinEnemyLoseUserPoints : player ? userWinEnemyWinUserPoints : enemies == 0 ? userLoseEnemyLoseUserPoints : userLoseEnemyWinUserPoints;
        activeUser.setPoints(points);
        log.info("setPoints. User {} choose {}, result={}, set {} points, round={}", activeUser.getName(), activeUser.getChoiceByRound(round), playerResult, points, round);
    }


    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public ComputerPlayer getEnemyByNumber(int number) {
        return activeEnemies.get(number);
    }

    public int getEnemiesSize() {
        return activeEnemies.size();
    }
}
