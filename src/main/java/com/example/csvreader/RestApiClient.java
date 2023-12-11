package com.example.csvreader;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestApiClient {

    public void postDataToApi(String apiUrl, String jsonData) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonData.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Handle the response if needed
        int responseCode = connection.getResponseCode();
        System.out.println("API Response Code: " + responseCode);

        connection.disconnect();
    }
}

