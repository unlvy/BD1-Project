import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        primaryStage.setTitle("Projekt BD Adam Laba");
        primaryStage.setScene(new Scene(root, 1000, 400));
        primaryStage.show();
    }

    @Override
    public void stop() {
        if (controller != null)
            controller.stop();
    }


    public static void main(String[] args) { launch(args); }


}
