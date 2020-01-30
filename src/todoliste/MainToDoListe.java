package todoliste;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import todoliste.view.BearbeiteEintragToDoListeController;

public class MainToDoListe extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/BearbeiteEintragToDoListe.fxml"));
        primaryStage.setTitle("ToDo Liste");
        primaryStage.setScene(new Scene(root, 570, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
