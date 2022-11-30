module com.example.planetguessrfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.planetguessrfx to javafx.fxml;
    exports com.example.planetguessrfx;
}