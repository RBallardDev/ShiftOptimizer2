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
    exports View.XMLControllers;
    opens View.XMLControllers to javafx.fxml;
    exports Controller.ShiftAssignment;
    opens Controller.ShiftAssignment to javafx.fxml;
    exports Controller.UserAuth;
    opens Controller.UserAuth to javafx.fxml;
}