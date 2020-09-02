package edge.of.luck.controllers;

import edge.of.luck.classes.GameLogic;
import edge.of.luck.classes.UsersHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class LoginController {
    public TextField loginText;
    @Autowired
    private GameLogic gameLogic;
    @Autowired
    private UsersHelper usersHelper;
    private final Logger log = LoggerContext.getContext().getLogger("LoginController");

    @FXML
    private void initialize() {
    }


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
