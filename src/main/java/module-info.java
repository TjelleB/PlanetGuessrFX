module com.example.planetguessrfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.planetguessrfx to javafx.fxml;
    exports com.example.planetguessrfx;
}