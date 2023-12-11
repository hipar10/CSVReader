module com.example.csvreader {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.web;
    requires java.sql;
    requires java.net.http;

    opens com.example.csvreader to javafx.fxml;

    exports com.example.csvreader;
}
