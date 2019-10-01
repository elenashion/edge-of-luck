package main.java.entities;

public enum GameResult {
    WIN("Вы угадали"),
    LOSE("Вы не угадали");

    String result;

    public String getResult() { return result; }

    GameResult(String result) { this.result = result; }
}
