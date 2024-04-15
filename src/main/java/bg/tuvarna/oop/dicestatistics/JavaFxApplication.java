package bg.tuvarna.oop.dicestatistics;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;

public class JavaFxApplication extends Application {
    private AppContext context;
    private Parent rootNode;

    @Override
    public void init() throws Exception {
        AppContext.getInstance().setContext(SpringApplication.run(DiceApplication.class));
        FXMLLoader fxmlLoader = new FXMLLoader(JavaFxApplication.class.getResource("/fxml/simulation.fxml"));
        fxmlLoader.setControllerFactory(AppContext.getInstance().getContext()::getBean);
        rootNode = fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(rootNode));
        stage.show();
    }

    @Override
    public void stop() {
        Platform.exit();
    }

    public static void main(String[] args) {
        launch();
    }
}
