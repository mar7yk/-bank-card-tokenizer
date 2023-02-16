module com.example.bank_card_tokenizer_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.bank_card_tokenizer_app to javafx.fxml;
    exports com.bank_card_tokenizer_app;
    exports com;
    opens com to javafx.fxml;
    exports com.bank_card_tokenizer_app.Controllers;
    opens com.bank_card_tokenizer_app.Controllers to javafx.fxml;
}