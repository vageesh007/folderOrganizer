package com.vageesh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class OrganizerApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/organizer.fxml"));
        Scene scene = new Scene(loader.load(), 600, 350);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        OrganizerController ctrl = loader.getController();
        ctrl.setStage(stage);

        stage.setTitle("Folder Organizer");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
