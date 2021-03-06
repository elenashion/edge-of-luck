package edge.of.luck.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import edge.of.luck.classes.GameLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuController {
    public Button logoutButton;
    public Button startGameButton1;
    public Button startGameButton2;
    public Button startGameButton3;
    private final GameLogic gameLogic;

    MenuController(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @PostMapping("/logout")
    public void logout() {
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

    public void exit() {
        log.info("Exit from program");
        System.exit(0);
    }

    @PostMapping("/getRecords")
    public void getRecords() {
    }

    @GetMapping("/getRules")
    public void getRules() {
    }

    @PostMapping("/chooseEnemies")
    public void chooseEnemies() {
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


    @PostMapping("/startGame")
    public void startGame() {
/*        try {
            int enemiesNumber = Integer.parseInt(((Button) actionEvent.getTarget()).getText());
            gameLogic.createEnemies(enemiesNumber);

            Stage stage = (Stage) ((Button) actionEvent.getTarget()).getScene().getWindow();
            GridPane rootLayout = FXMLLoader.load(getClass().getResource("/main/edge.of.luck.fxml/GameWindow.edge.of.luck.fxml"));
            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
