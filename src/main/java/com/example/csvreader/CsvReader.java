// CsvReader.java
package com.example.csvreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class CsvReader {

    private static final String API_ENDPOINT = "http://your-api-endpoint";

    public static void main(String[] args) {
        try {
            List<String> csvRows = readCsvFile("C:\\Users\\himes\\OneDrive\\Documents\\reader.csv");
            sendRowsToApi(csvRows);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readCsvFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines().toList();
        }
    }

    private static void sendRowsToApi(List<String> csvRows) {
        HttpClient client = HttpClient.newHttpClient();

        csvRows.forEach(row -> {
            // Assuming each row is a comma-separated values (CSV)
            List<String> fields = Arrays.asList(row.split(","));

            // Convert fields to JSON format
            String jsonPayload = String.format(
                    "{\"customerRef\":\"%s\",\"customerName\":\"%s\",\"addressLine1\":\"%s\",\"addressLine2\":\"%s\",\"town\":\"%s\",\"county\":\"%s\",\"country\":\"%s\",\"postcode\":\"%s\"}",
                    fields.get(1).trim(), fields.get(3).trim(), fields.get(5).trim(), fields.get(7).trim(), fields.get(9).trim(),
                    fields.get(11).trim(), fields.get(13).trim(), fields.get(15).trim()
            );

            // Send JSON payload to API endpoint
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_ENDPOINT))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("API Response: " + response.body());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
