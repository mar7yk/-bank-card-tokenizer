package com.bank_card_tokenizer_app;

import com.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.SocketException;

public class Application extends javafx.application.Application {

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Client.getInstants().close();
    }

    @Override
    public void start(Stage stage) throws IOException {

        if (Client.getInstants() == null) {
            Stage errorStage = Stages.getErrorStage();
            errorStage.show();

        } else {
            Stage loginStage = Stages.getLoginStage();
            loginStage.show();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}