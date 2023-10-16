module com.connerum.modernprice {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.connerum.modernprice to javafx.fxml;
    exports com.connerum.modernprice;
    exports com.connerum.modernprice.Controller;
    opens com.connerum.modernprice.Controller to javafx.fxml;
}