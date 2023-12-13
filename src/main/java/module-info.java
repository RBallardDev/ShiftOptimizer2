module com.example.shiftoptimizer2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.csv;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.bouncycastle.provider;
    requires com.fasterxml.jackson.databind;

    opens Controller to javafx.fxml;
    exports Controller;
    exports Controller.XMLControllers;
    opens Controller.XMLControllers to javafx.fxml;
}