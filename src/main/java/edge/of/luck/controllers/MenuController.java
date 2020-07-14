package edge.of.luck.controllers;

import edge.of.luck.EdgeOfLuckMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import edge.of.luck.classes.GameLogic;
import edge.of.luck.classes.LogicManager;

import java.io.IOException;

public class MenuController {
    public Button logoutButton;
    public Button startGameButton1;
    public Button startGameButton2;
    public Button startGameButton3;
    private LogicManager logicManager;
    private GameLogic gameLogic;


    @FXML
    private void initialize() {
        this.logicManager = EdgeOfLuckMain.logicManager;
        this.gameLogic = EdgeOfLuckMain.gameLogic;
    }

    public void logout(ActionEvent actionEvent) {
        try {
            logicManager.log.info(String.format("User %s is logout", gameLogic.getActiveUser().getName()));
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            GridPane rootLayout = FXMLLoader.load(getClass().getResource("/main/edge.of.luck.fxml/LoginWindow.edge.of.luck.fxml"));
            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit(ActionEvent actionEvent) {
        logicManager.log.info("Exit from program");
        System.exit(0);
    }

    public void getRecords(ActionEvent actionEvent) {
    }

    public void getRules(ActionEvent actionEvent) {
    }

    public void chooseEnemies(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            GridPane rootLayout = FXMLLoader.load(getClass().getResource("/main/edge.of.luck.fxml/ChooseEnemiesWindow.edge.of.luck.fxml"));
            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void startGame(ActionEvent actionEvent) {
        try {
            int enemiesNumber = Integer.parseInt(((Button) actionEvent.getTarget()).getText());
            gameLogic.createEnemies(enemiesNumber);

            Stage stage = (Stage) ((Button) actionEvent.getTarget()).getScene().getWindow();
            GridPane rootLayout = FXMLLoader.load(getClass().getResource("/main/edge.of.luck.fxml/GameWindow.edge.of.luck.fxml"));
            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
