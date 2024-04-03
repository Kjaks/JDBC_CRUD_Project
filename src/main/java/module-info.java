module com.karolis.jdbc_sql_kj {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires mysql.connector.j;

    opens com.karolis.jdbc_sql_kj to javafx.fxml;
    exports com.karolis.jdbc_sql_kj;
    exports com.karolis.jdbc_sql_kj.TableInfo;
    opens com.karolis.jdbc_sql_kj.TableInfo to javafx.fxml;
    exports com.karolis.jdbc_sql_kj.Controller;
    opens com.karolis.jdbc_sql_kj.Controller to javafx.fxml;
}