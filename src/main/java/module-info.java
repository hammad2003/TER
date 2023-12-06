module com.example.ter {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ter to javafx.fxml;
    exports com.example.ter;
}