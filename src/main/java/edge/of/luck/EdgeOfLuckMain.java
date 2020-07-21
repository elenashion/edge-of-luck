package edge.of.luck;

import edge.of.luck.classes.GameLogic;
import edge.of.luck.classes.LogicManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EdgeOfLuckMain extends Application {
    public static LogicManager logicManager;
    public static GameLogic gameLogic;
    private Stage primaryStage;
    private GridPane rootLayout;

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/main.properties"));
        logicManager = new LogicManager(properties);
        gameLogic = logicManager.gameLogic;

        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Edge of Luck");

        initRootLayout();
    }


    private void initRootLayout() {
        try {
            rootLayout = FXMLLoader.load(getClass().getResource("/main/edge/of/luck/fxml/LoginWindow.fxml"));
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
