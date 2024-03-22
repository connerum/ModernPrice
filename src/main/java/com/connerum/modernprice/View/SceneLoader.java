package com.connerum.modernprice.View;

import com.connerum.modernprice.Controller.LicenseController;
import com.connerum.modernprice.Controller.MainController;
import com.connerum.modernprice.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class SceneLoader {
    private static double xOffset = 0;
    private static double yOffset = 0;
    public static void loadMainScene(Stage stage) throws IOException {
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

        //stage.setTitle("SURV ModernPrice - Offline Labels Printer");
        stage.initStyle(StageStyle.TRANSPARENT);

        // Set the application icon
        Image icon = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("images/barcodeIcon.icns")));
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();
    }

    public static void loadActivationScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("license-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 775, 560);

        // Make the background of the scene transparent
        scene.setFill(null);

        LicenseController controller = fxmlLoader.getController();

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

}
