package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);//точка входа в FX приложение
    }

    @Override
    //метод класса application - вызывается при создании потока приложения
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        primaryStage.setScene(new Scene(root)); // установка Scene для Stage
        primaryStage.sizeToScene();
        primaryStage.setTitle("Computational practicum"); //title of the window
        primaryStage.show();
    }
}
