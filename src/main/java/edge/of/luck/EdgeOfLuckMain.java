package edge.of.luck;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class EdgeOfLuckMain extends Application {
    private Stage primaryStage;
    private GridPane rootLayout;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
//        launch(args);
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
