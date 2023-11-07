package com.connerum.modernprice;

import com.connerum.modernprice.Controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    public static final String APP_VERSION = "1.0.1";

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 775, 560);

        // Make the background of the scene transparent
        scene.setFill(null);

        MainController controller = fxmlLoader.getController();

        // Get the toolbar from the controller
        ToolBar myToolbar = controller.getToolbar();

        // Capture mouse press and drag events on the toolbar
        myToolbar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        myToolbar.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        stage.setTitle("SURV ModernPrice - Offline Labels Printer");
        stage.initStyle(StageStyle.TRANSPARENT);

        // Set the application icon
        Image icon = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/barcodeIcon.icns")));
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
