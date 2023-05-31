module com.emsi.tache_manage {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires org.json;
    requires com.fasterxml.jackson.databind;

    opens com.emsi.tache_manage to javafx.fxml;
    exports com.emsi.tache_manage;

    exports com.emsi.tache_manage.Controller to javafx.fxml;

    opens com.emsi.tache_manage.Controller to javafx.fxml;

    opens com.emsi.tache_manage.entities to javafx.base;


}