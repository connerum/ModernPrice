package com.connerum.modernprice.Model;

import com.connerum.modernprice.MainApplication;

import java.io.BufferedReader;
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
}
