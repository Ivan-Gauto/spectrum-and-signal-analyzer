import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        SignalAnalyzer analyzer = new SignalAnalyzer();
        Scene scene = new Scene(analyzer, 900, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Analizador de Señales");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

