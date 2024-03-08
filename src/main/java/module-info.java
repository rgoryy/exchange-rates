module com.example.exchangerates {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.exchangerates to javafx.fxml;
    exports com.example.exchangerates;
}