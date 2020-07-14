package edge.of.luck.entities;

import java.util.HashMap;
import java.util.Map;

public class User {
    private final String name;
    private int points;
    private Map<Integer, GameChoice> decisions;
    private Map<Integer, GameResult> results;

    public Map<Integer, GameResult> getResults() {
        return results;
    }

    public Map<Integer, GameChoice> getDecisions() {
        return decisions;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public GameResult getResultByRound(int round) {
        return results.get(round);
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
