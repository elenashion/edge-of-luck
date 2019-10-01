package main.controllers;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.GameLogic;
import main.java.Main;
import main.java.entities.GameChoice;

import java.io.IOException;

public class GameController {
    private GameLogic gameLogic;
    public Text firstEnemyName;
    public Text playerName;
    public Text firstEnemyPoints;
    public Text playerPoints;
    public Text textAboveCubes;
    public Text textUnderCubes;
    public Text firstCube;
    public Text secondCube;
    public Button oddButton;
    public Button evenButton;

    private final String startMsgAboveCubes = "Числа разыграны! Выберите \"чёт\" или \"нечет\"";
    public Text secondEnemyName;
    public Text secondEnemyPoints;
    public Text thirdEnemyName;
    public Text thirdEnemyPoints;

    @FXML
    private void initialize() {
        this.gameLogic = Main.gameLogic;
        gameLogic.resetRound();

        gameLogic.setNumbers();
        setStartVars();
    }

    private void setStartVars() {
        playerName.setText(gameLogic.getActiveUser().getName());
        playerPoints.setText(String.valueOf(gameLogic.getActiveUser().getPoints()));
        firstEnemyName.setText(gameLogic.getEnemyByNumber(0).getName());
        firstEnemyPoints.setText(String.valueOf(gameLogic.getEnemyByNumber(0).getPoints()));
        if (gameLogic.getEnemiesSize() >= 2) {
            secondEnemyName.setText(gameLogic.getEnemyByNumber(1).getName());
            secondEnemyPoints.setText(String.valueOf(gameLogic.getEnemyByNumber(1).getPoints()));
        }
        if (gameLogic.getEnemiesSize() == 3) {
            thirdEnemyName.setText(gameLogic.getEnemyByNumber(2).getName());
            thirdEnemyPoints.setText(String.valueOf(gameLogic.getEnemyByNumber(2).getPoints()));
        }
        textAboveCubes.setText(startMsgAboveCubes);
        textUnderCubes.setText(" ");
        firstCube.setText(" ");
        secondCube.setText(" ");

    }

    public void chooseOdd(ActionEvent actionEvent) {
        blockButtons();
        String text = gameLogic.getResultStringFromUserDecision(GameChoice.ODD);
        afterChoose(text);
    }

    private void finishedSleeping() {}

    public void chooseEven(ActionEvent actionEvent) {
        blockButtons();
        String text = gameLogic.getResultStringFromUserDecision(GameChoice.EVEN);
        afterChoose(text);
    }

    private void blockButtons() {
        oddButton.setDisable(true);
        evenButton.setDisable(true);
    }

    private void unblockButtons() {
        oddButton.setDisable(false);
        evenButton.setDisable(false);
    }

    private void afterChoose(String text) {
        if (text == null) {
            unblockButtons();
            setStartVars();
        }

        textAboveCubes.setText("Результаты раунда:");
        firstCube.setText(String.valueOf(gameLogic.getFirstNumber()));
        secondCube.setText(String.valueOf(gameLogic.getSecondNumber()));
        textUnderCubes.setText(text);
        Task timer = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(2 * 1_000);
                    unblockButtons();
                    setStartVars();
                    gameLogic.setNumbers();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        timer.setOnSucceeded(event -> finishedSleeping());
        new Thread(timer).start();
    }

    public void returnToMenu(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Button) actionEvent.getTarget()).getScene().getWindow();
            GridPane rootLayout = FXMLLoader.load(getClass().getResource("/main/fxml/MenuWindow.fxml"));

            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
