package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.java.LogicManager;
import main.java.Main;

import java.io.IOException;

public class LoginController {
    public TextField loginText;
    private LogicManager logicManager;

    @FXML
    private void initialize() {
        this.logicManager = Main.logicManager;
    }


    public void login(ActionEvent actionEvent) {
        try {
            if (loginText.getText().length() < 3)
                return;

            logicManager.gameLogic.setActiveUser(logicManager.getOrCreateUser(loginText.getText()));

            Stage stage = (Stage) loginText.getScene().getWindow();
            GridPane rootLayout = FXMLLoader.load(getClass().getResource("/main/fxml/MenuWindow.fxml"));
            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
