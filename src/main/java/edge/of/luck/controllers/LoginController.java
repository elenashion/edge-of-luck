package edge.of.luck.controllers;

import edge.of.luck.EdgeOfLuckMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import edge.of.luck.classes.LogicManager;

import java.io.IOException;

public class LoginController {
    public TextField loginText;
    private LogicManager logicManager;

    @FXML
    private void initialize() {
        this.logicManager = EdgeOfLuckMain.logicManager;
    }


    public void login(ActionEvent actionEvent) {
        try {
            if (loginText.getText().length() < 3)
                return;

            logicManager.gameLogic.setActiveUser(logicManager.getUserByName(loginText.getText()));

            Stage stage = (Stage) loginText.getScene().getWindow();
            GridPane rootLayout = FXMLLoader.load(getClass().getResource("/main/edge.of.luck.fxml/MenuWindow.edge.of.luck.fxml"));
            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
