package edge.of.luck.entities;

import edge.of.luck.entities.enums.GameChoice;
import edge.of.luck.entities.enums.GameResult;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class User {
    @Getter
    private final String name;
    @Getter
    private int points;
    @Getter
    @Setter
    private int roundNumber = 1;
    private final Map<Integer, GameChoice> decisions = new HashMap<>();
    private final Map<Integer, GameResult> results = new HashMap<>();

    public void putResult(int round, @NonNull GameResult result) {
        results.put(round, result);
    }

    public void putDecisions(int round, @NonNull GameChoice choice) {
        decisions.put(round, choice);
    }

    public GameChoice getChoiceByRound(int round) {
        return decisions.get(round);
    }

    public void setPoints(int point) {
        points += point;
        if (points < 0)
            points = 0;
    }

    public User(@NonNull String name) {
        this.name = name;
        this.points = 100;
    }

}
