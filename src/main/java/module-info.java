module com.example.shiftoptimizer2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.fasterxml.jackson.databind;
    requires org.bouncycastle.provider;

    opens Controller to javafx.fxml;
    exports Controller;
    exports Controller.FIle;
    opens Controller.FIle to javafx.fxml;
}