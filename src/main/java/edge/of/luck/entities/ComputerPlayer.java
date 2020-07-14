package edge.of.luck.entities;

import edge.of.luck.classes.GameLogic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComputerPlayer {
    private String name;
    private int points;
    private int number;
    private Map<Integer, GameChoice> decisions;
    private Map<Integer, GameResult> results;

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

    public void setPoints(int point) {
        points += point;
        if (points < 0)
            points = 0;
    }

    public void putResult(int round, GameResult result) {
        results.put(round, result);
    }

    public ComputerPlayer(GameLogic gameLogic, int number) {
        this.number = number;
        this.name = setName(gameLogic);
        this.points = 100;
        decisions = new HashMap<>();
        results = new HashMap<>();
    }

    private String setName(GameLogic gameLogic) {
        List<String> names = Arrays.asList("Мадока", "Курису", "Маюши", "Хоро", "Харухи", "Коната", "Аква", "Йоко", "Рей", "Мику");
        int number = gameLogic.getRandom() / 10;
        return number == 0 ? names.get(0) : number == 1 ? names.get(1) : number == 2 ? names.get(2) : number == 3 ? names.get(3) : number == 4 ? names.get(4) : number == 5 ? names.get(5) :
                number == 6 ? names.get(6) : number == 7 ? names.get(7) : number == 8 ? names.get(8) : number == 9 ? names.get(9) : names.get(0);
    }

    public GameChoice makeADecision(int round) {
        switch (number) {
            case 0:
                if (round % 5 == 0)
                    return makeSpecialDecision(round) == GameChoice.EVEN ? GameChoice.ODD : GameChoice.EVEN;
                else
                    return makeSpecialDecision(round);
            case 1:
                return makeADecisionWithoutList(round);
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
