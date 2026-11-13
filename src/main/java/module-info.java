module com.schooltracker.practica5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.schooltracker.practica5 to javafx.fxml;
    exports com.schooltracker.practica5;
}