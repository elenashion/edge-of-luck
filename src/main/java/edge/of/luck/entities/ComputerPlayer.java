package edge.of.luck.entities;

import edge.of.luck.classes.GameLogic;
import edge.of.luck.entities.enums.GameChoice;
import edge.of.luck.entities.enums.GameResult;

import java.util.*;

public class ComputerPlayer {
    private final String name;
    private int points;
    private final int number;
    private final Map<Integer, GameChoice> decisions;
    private final Map<Integer, GameResult> results;

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public GameResult getResultByRound(int round) {
        return results.get(round);
    }

    public GameChoice getChoiceByRound(int round) {
        return decisions.get(round);
    }

    List<String> names = Arrays.asList("Madoka", "Kurisu", "Mayushi", "Horo", "Haruhi", "Konata", "Aqua", "Yoko", "Rei", "Miku");

    public void setPoints(int point) {
        points += point;
        if (points < 0)
            points = 0;
    }

    public void putResult(int round, GameResult result) {
        results.put(round, result);
    }

    public ComputerPlayer(int number, Set<String> existingEnemyNames) {
        this.number = number;
        this.name = setName(existingEnemyNames);
        this.points = 100;
        decisions = new HashMap<>();
        results = new HashMap<>();
    }

    private String setName(Set<String> existingEnemyNames) {
        String newName;
        do {
            int number = GameLogic.getRandom() / 10;
            newName = names.get(number);
        } while (existingEnemyNames.contains(newName));
        return newName;
    }

    public GameChoice makeADecision(int round) {
        switch (number) {
            case 0:
                if (round % 5 == 0)
                    return makeSpecialDecision(round) == GameChoice.EVEN ? GameChoice.ODD : GameChoice.EVEN;
                else
                    return makeSpecialDecision(round);
            case 2:
                if (round % 10 == 0)
                    return makeSpecialDecision(round);
                else
                    return makeSpecialDecision(round) == GameChoice.EVEN ? GameChoice.ODD : GameChoice.EVEN;
            default:
                return makeADecisionWithoutList(round);
        }
    }

    private GameChoice makeSpecialDecision(int round) {
        if (decisions == null || decisions.isEmpty())
            return makeADecisionWithoutList(round);
        else
            return makeADecisionWithList(round);
    }

    private GameChoice makeADecisionWithList(int round) {
        if (decisions.size() < 2)
            return makeADecisionWithoutList(round);
        GameChoice result;
        // если последний раз был победным
        if (results.get(round - 1) == GameResult.WIN) {
            if (results.get(round - 2) == GameResult.WIN) {
                // если два последних раунда мы угадывали, то смотрим, что выбирали. Если угадывали с одинаковыми выборами, то выбираем противоположный,
                // если выборы были разными, то берем последний и выбираем его снова
                if (decisions.get(round - 1) == decisions.get(round - 2)) {
                    result = decisions.get(round - 1) == GameChoice.EVEN ? GameChoice.ODD : GameChoice.EVEN;
                } else {
                    result = decisions.get(round - 1);
                }

                // если в последний раз мы угадали, а до этого - нет, пробуем последний вариант ещё раз
            } else {
                result = decisions.get(round - 1);
            }
            // если последний раз был проигрышным
        } else {
            if (decisions.get(round - 1) == decisions.get(round - 2)) {
                // если два раза подряд проигрывали и выбирали одинаковый вариант, то выбираем его же
                result = decisions.get(round - 1);
            } else {
                result = decisions.get(round - 1) == GameChoice.EVEN ? GameChoice.ODD : GameChoice.EVEN;
            }
        }

        decisions.put(round, result);
        return result;
    }

    private GameChoice makeADecisionWithoutList(int round) {
        // первые пару раз у нас нет данных для анализа, выбираем случайный вариант
        GameChoice result = (Math.random()) % 2 == 1 ? GameChoice.EVEN : GameChoice.ODD;
        decisions.put(round, result);
        return result;
    }
}
