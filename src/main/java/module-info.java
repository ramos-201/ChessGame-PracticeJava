module com.andresramos.chessgamepracticejava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.andresramos.chessgamepracticejava to javafx.fxml;
    // exports com.andresramos.chessgamepracticejava;
}