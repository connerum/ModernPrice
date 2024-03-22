package com.connerum.modernprice.Model;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LicenseValidator {
    private static final String ACTIVATION_URL = "https://api.modernprice.pro/activate-license/";
    private static final String CHECK_URL = "https://api.modernprice.pro/check-license/";

    public static boolean checkLicenseKey(String licenseKey) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(CHECK_URL + "?merchant_id=" + licenseKey))
                    .header("Accept", "application/json") // Ensure the server knows we want JSON
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.body());
            String message = jsonResponse.optString("message", "");
            return "Merchant ID is valid and activated".equals(message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validateLicenseKey(String licenseKey) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ACTIVATION_URL + licenseKey))
                    .header("Accept", "application/json") // Ensure the server knows we want JSON
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.body());
            String message = jsonResponse.optString("message", "");
            return "License activated successfully".equals(message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
