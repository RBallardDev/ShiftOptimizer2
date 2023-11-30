module com.example.shiftoptimizer2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens Controller to javafx.fxml;
    exports Controller;
}