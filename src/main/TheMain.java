package main;

import controllerview.AdressbuchC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TheMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AdressbuchC.show(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
