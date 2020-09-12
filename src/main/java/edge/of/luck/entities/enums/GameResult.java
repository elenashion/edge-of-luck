package edge.of.luck.entities.enums;

public enum GameResult {
    // TODO: remove string, add locale versions by result
    WIN("You won"),
    LOSE("You lose");

    String result;

    public String getResult() {
        return result;
    }

    GameResult(String result) {
        this.result = result;
    }
}
