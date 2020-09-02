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
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/menu")
public class MenuController {
    public Button logoutButton;
    public Button startGameButton1;
    public Button startGameButton2;
    public Button startGameButton3;
    @Autowired
    private GameLogic gameLogic;
    private final Logger log = LoggerContext.getContext().getLogger("MenuController");


    @FXML
    private void initialize() {
    }

    public void logout(ActionEvent actionEvent) {
        try {
            log.info(String.format("User %s is logout", gameLogic.getActiveUser().getName()));
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            GridPane rootLayout = FXMLLoader.load(getClass().getResource("/main/edge/of/luck/fxml/LoginWindow.fxml"));
            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit(ActionEvent actionEvent) {
        log.info("Exit from program");
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
