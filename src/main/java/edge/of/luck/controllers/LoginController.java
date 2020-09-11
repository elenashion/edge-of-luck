package edge.of.luck.controllers;

import edge.of.luck.classes.GameLogic;
import edge.of.luck.classes.UsersHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/login")
public class LoginController {
    public TextField loginText;
    private final GameLogic gameLogic;
    private final UsersHelper usersHelper;
    private final Logger log = LoggerContext.getContext().getLogger("LoginController");

    LoginController(GameLogic gameLogic, UsersHelper usersHelper) {
        this.gameLogic = gameLogic;
        this.usersHelper = usersHelper;
    }

    @PostMapping("/register")
    public void register() {

    }

    @PostMapping("/login")
    public void login(ActionEvent actionEvent) {
        try {
            if (loginText.getText().length() < 3)
                return;

            gameLogic.setActiveUser(usersHelper.getOrCreateUserByName(loginText.getText()));

            Stage stage = (Stage) loginText.getScene().getWindow();
            GridPane rootLayout = FXMLLoader.load(getClass().getResource("/main/edge/of/luck/fxml/MenuWindow.fxml"));
            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
