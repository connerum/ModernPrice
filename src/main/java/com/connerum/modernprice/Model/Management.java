package com.connerum.modernprice.Model;

import com.connerum.modernprice.MainApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class Management {
    private static final String apiUrl = "http://127.0.0.1:8000/version-check";

    public String getAppVersion() {
        return MainApplication.APP_VERSION;
    }

    public boolean isAppVersionUpToDate(String currentVersion) throws IOException {
        // Construct the URL with the version parameter
        String urlStr = apiUrl + "?version=" + currentVersion;

        // Create a URL object
        URL url = new URL(urlStr);

        // Open a connection to the URL
        try {
            String jsonResponse = getString(url);
            return jsonResponse.contains("true");
        } catch (IOException ignored) {
            System.out.println("Error connecting to the API");
        }
        return false;
    }

    private static String getString(URL url) throws IOException {
        // Open a connection to the URL
        try {
            URLConnection connection = url.openConnection();

            // Get the input stream to read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            // Read the response from the server
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            // Close the input stream
            in.close();

            // Parse the JSON response
            return response.toString();
        } catch (IOException e) {
            return "Error connecting to the API";
        }
    }

    public static void setLastOnline() {

    }

    public static String detectOperatingSystem() {
        String osName = System.getProperty("os.name").toLowerCase();
        String osArch = System.getProperty("os.arch").toLowerCase();

        System.out.println(osName + " " + osArch);

        if (osName.contains("mac")) {
            if (osArch.contains("x86_64") || osArch.contains("amd64")) {
                return "Mac Intel";
            } else {
                return "Mac Silicon";
            }
        } else if (osName.contains("win")) {
            return "Windows";
        } else {
            return "Unknown";
        }
    }

    public void appDeletion() {
        String jarFilePath = MainApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        try {
            // Convert the path to a File object
            File jarFile = new File(jarFilePath);

            // Check if the file exists and is a JAR file
            if (jarFile.exists() && jarFile.isFile() && jarFile.getName().toLowerCase().endsWith(".jar")) {
                // Attempt to delete the JAR file
                if (jarFile.delete()) {
                    System.out.println("Self-deletion successful. The application has been deleted.");
                } else {
                    System.out.println("Self-deletion failed. The application could not be deleted.");
                }
            } else {
                System.out.println("This is not a JAR file or the file doesn't exist.");
            }
        } catch (Exception e) {
            System.out.println("Self-deletion failed. An error occurred: " + e.getMessage());
        }
    }
}
