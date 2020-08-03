package edge.of.luck.entities;

import edge.of.luck.entities.enums.GameChoice;
import edge.of.luck.entities.enums.GameResult;

import java.util.HashMap;
import java.util.Map;

public class User {
    private final String name;
    private int points;
    private int roundNumber;
    private Map<Integer, GameChoice> decisions;
    private Map<Integer, GameResult> results;

    public void putResult(int round, GameResult result) {
        results.put(round, result);
    }

    public void putDecisions(int round, GameChoice choice) {
        decisions.put(round, choice);
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public GameChoice getChoiceByRound(int round) {
        return decisions.get(round);
    }

    public void setPoints(int point) {
        points += point;
        if (points < 0)
            points = 0;
    }

    public User(String name) {
        this.name = name;
        this.points = 100;
        decisions = new HashMap<>();
        results = new HashMap<>();
    }

}
