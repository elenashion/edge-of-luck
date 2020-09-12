package edge.of.luck.entities;

import lombok.Data;

@Data
public class GameResultPoints {
    private int userWinEnemyLose;
    private int userWinEnemyWin;
    private int userLoseEnemyWin;
    private int userLoseEnemyLose;
}
