module com.connerum.modernprice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.slf4j;
    requires java.net.http;
    requires java.prefs;
    requires org.json;


    opens com.connerum.modernprice to javafx.fxml;
    exports com.connerum.modernprice;
    exports com.connerum.modernprice.Controller;
    opens com.connerum.modernprice.Controller to javafx.fxml;
}