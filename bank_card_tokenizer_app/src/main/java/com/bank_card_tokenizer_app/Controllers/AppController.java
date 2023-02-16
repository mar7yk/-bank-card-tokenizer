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

public class AppController {
    @FXML
    public Label lblCardNumber;
    @FXML
    public Label lblToken;
    @FXML
    public TextField txtCardNumber;
    @FXML
    public TextField txtToken;
    @FXML
    public Button btnRegisterToken;
    @FXML
    public Button btnGetCardNumber;

    @FXML
    public void btnRegisterToken() throws IOException {
        String token = Client.getInstants().registerToken(txtCardNumber.getText());
        txtToken.setText(token);
    }

    @FXML
    public void btnGetCardNumber() throws IOException {
        String token = Client.getInstants().getCardNum(txtToken.getText());
        txtCardNumber.setText(token);
    }

    @FXML
    public void btnLogout() throws IOException {
        Stage stage = Stages.getLoginStage();
        stage.show();
    }
}