package todoliste;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import todoliste.datenbank.Datenbank;

public class MainToDoListe extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/ToDoListe.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        ////////////////////////
        Datenbank.getInstance().connect(); //Test Erstellung der Datenbank
        ////////////////////////
        launch(args);
    }

    @Override
    public void stop() {
        // Trennen der Datenbankverbindung
        Datenbank.getInstance().disconnect();
    }
}
