package edge.of.luck.classes;

import edge.of.luck.configuration.PointsProperties;
import edge.of.luck.entities.ComputerPlayer;
import edge.of.luck.entities.GameResultPoints;
import edge.of.luck.entities.enums.GameChoice;
import edge.of.luck.entities.enums.GameResult;
import edge.of.luck.entities.User;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class GameLogic {

    private int round = 0;

    private int firstNumber;
    private int secondNumber;
    private GameChoice roundResult;

    private static final int MAX_ENEMIES_SIZE = 3;
    private User activeUser;
    private final List<ComputerPlayer> activeEnemies = new ArrayList<>();

    private final GameResultPoints userPoints;
    private final GameResultPoints enemyPoints;


    public GameLogic(PointsProperties points) {
        this.userPoints = points.getUser();
        this.enemyPoints = points.getEnemy();
    }

    public static int getRandom() {
        return Integer.parseInt(new BigDecimal(Math.random() * 100).abs().divide(BigDecimal.ONE, 0, RoundingMode.DOWN).toString());
    }

    public void createEnemies(int number) {
        if (number <= 0 || number > MAX_ENEMIES_SIZE) {
            log.error("Enemies number is incorrect! Number={}", number);
            return;
        }
        activeEnemies.clear();
        for (int i = 0; i < number; i++) {
            activeEnemies.add(i, new ComputerPlayer(i, activeEnemies.stream().map(ComputerPlayer::getName).collect(Collectors.toSet())));
        }
        log.info("Enemies created. Number of enemies={}", number);
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setNumbers () {
        round++;
        activeUser.setRoundNumber(round);
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
        activeUser.putDecisions(round, choice);
        GameResult playerResult = roundResult == choice ? GameResult.WIN : GameResult.LOSE;
        activeUser.putResult(round, playerResult);
        log.info("getResultStringFromUserDecision. PlayerResult={}, activeUser={}", playerResult, activeUser.getName());
        setPoints(playerResult);
        return playerResult.getResult();
    }

    public void resetRound() {
        round = activeUser.getRoundNumber();
    }

    private void setPoints(GameResult playerResult) {
        boolean player = playerResult == GameResult.WIN;
        int enemies = 0;
        for (ComputerPlayer cp : activeEnemies) {
            int points;
            if (player && cp.getResultByRound(round) == GameResult.WIN) {
                points = enemyPoints.getUserWinEnemyWin();
                enemies++;
            } else if (player && cp.getResultByRound(round) == GameResult.LOSE) {
                points = enemyPoints.getUserWinEnemyLose();
            } else if (!player && cp.getResultByRound(round) == GameResult.WIN) {
                points = enemyPoints.getUserLoseEnemyWin();
                enemies++;
            } else {
                points = enemyPoints.getUserLoseEnemyLose();
            }
            cp.setPoints(points);
            log.info("setPoints. {} choose {}, result={}, set {} points. Round={}, activeUser={}",
                    cp.getName(), cp.getChoiceByRound(round), cp.getResultByRound(round), points, round, activeUser.getName());
        }

        int points = (player && enemies == 0) ? userPoints.getUserWinEnemyLose() : player ? userPoints.getUserWinEnemyWin() : enemies == 0 ? userPoints.getUserLoseEnemyLose() : userPoints.getUserLoseEnemyWin();
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
