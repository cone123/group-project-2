module com.example.groupproject_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;


    opens com.example.groupproject_2 to javafx.fxml;
    exports com.example.groupproject_2;
}