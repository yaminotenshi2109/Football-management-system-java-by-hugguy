module com.example.fcms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.fcms to javafx.fxml;
    exports com.example.fcms;

}