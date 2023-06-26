module com.seshare.seshare {
    requires javafx.controls;
    requires javafx.fxml;
        requires javafx.web;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
            requires net.synedra.validatorfx;
                requires org.kordamp.bootstrapfx.core;
    requires jgroups;
    requires java.sql;
    requires java.desktop;
    //  requires eu.hansolo.tilesfx;
        
    opens com.seshare.seshare to javafx.fxml;
    exports com.seshare.seshare;
    opens dashboarddesign to javafx.fxml;
    exports dashboarddesign;
}