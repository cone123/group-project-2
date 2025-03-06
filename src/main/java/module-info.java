module com.example.groupproject_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.groupproject_2 to javafx.fxml;
    exports com.example.groupproject_2;
}