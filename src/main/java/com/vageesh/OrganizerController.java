package com.vageesh;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;

public class OrganizerController {
    @FXML private TextField pathField;
    @FXML private ProgressBar progressBar;
    @FXML private Label statusLabel;

    private Stage stage;
    public void setStage(Stage stage) { this.stage = stage; }

    @FXML
    public void onBrowse() {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Select Folder to Organize");
        File dir = dc.showDialog(stage);
        if (dir != null) {
            pathField.setText(dir.getAbsolutePath());
            statusLabel.setText("");
        }
    }

    @FXML
    public void onOrganize() {
        String p = pathField.getText();
        if (p == null || p.trim().isEmpty()) {
            statusLabel.setText("❌ Please select a folder first.");
            return;
        }

        OrganizerService task = new OrganizerService(Paths.get(p));
        progressBar.progressProperty().bind(task.progressProperty());
        statusLabel.textProperty().bind(task.messageProperty());

        task.setOnSucceeded(e -> {
            statusLabel.textProperty().unbind();
            statusLabel.setText("✔ Done organizing!");
            progressBar.progressProperty().unbind();
            progressBar.setProgress(1);
        });
        task.setOnFailed(e -> {
            statusLabel.textProperty().unbind();
            statusLabel.setText("❌ Failed: " + task.getException().getMessage());
            progressBar.progressProperty().unbind();
            progressBar.setProgress(0);
        });

        new Thread(task).start();
    }
}
