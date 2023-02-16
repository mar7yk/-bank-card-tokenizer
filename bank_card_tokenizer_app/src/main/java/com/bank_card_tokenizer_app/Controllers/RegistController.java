package com.bank_card_tokenizer_app.Controllers;

import com.Client;
import com.bank_card_tokenizer_app.Stages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistController {
    @FXML
    public Label lblUsername;
    @FXML
    public Label lblPassword;
    @FXML
    public TextField txtUsername;
    @FXML
    public TextField txtPassword;
    public Button btnRegist;


    @FXML
    public void btnSubmit() throws IOException {
        boolean isLogin = Client.getInstants().register(txtUsername.getText(), txtPassword.getText());

        if (isLogin) {
            Stage stage = Stages.getAppStage();
            stage.show();
        }
    }

    @FXML
    public void btnLogin() throws IOException {
        Stage stage = Stages.getLoginStage();
        stage.show();
    }
}