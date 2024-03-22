package com.connerum.modernprice;

import com.connerum.modernprice.Model.LicenseStorage;
import com.connerum.modernprice.Model.LicenseValidator;
import com.connerum.modernprice.View.SceneLoader;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    public static final String APP_VERSION = "1.0.3";

    @Override
    public void start(Stage stage) throws IOException {
        String licenseKey = LicenseStorage.getLicenseKey();


        if (licenseKey == null || !LicenseValidator.checkLicenseKey(licenseKey)) {
            SceneLoader.loadActivationScene(stage);
        } else {
            SceneLoader.loadMainScene(stage);
        }

    }

    public static void main(String[] args) {
        launch();
    }
}
