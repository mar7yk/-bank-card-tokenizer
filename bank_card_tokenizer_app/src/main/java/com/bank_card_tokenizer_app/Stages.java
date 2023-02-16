package com.bank_card_tokenizer_app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Stages {
    public static Stage stage;

    public static Stage getErrorStage() throws IOException {
        if (stage == null) {
            stage = new Stage();
            stage.setTitle("Bank card tokenizer");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("not-available-server-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        stage.setScene(scene);

        return stage;
    }

    public static Stage getLoginStage() throws IOException {
        if (stage == null) {
            stage = new Stage();
            stage.setTitle("Bank card tokenizer");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        stage.setScene(scene);

        return stage;
    }

    public static Stage getAppStage() throws IOException {
        if (stage == null) {
            stage = new Stage();
            stage.setTitle("Bank card tokenizer");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        stage.setScene(scene);

        return stage;
    }

    public static Stage getRegistStage() throws IOException {
        if (stage == null) {
            stage = new Stage();
            stage.setTitle("Bank card tokenizer");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("regist-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        stage.setScene(scene);

        return stage;
    }

}
