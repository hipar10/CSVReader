package com.example.csvreader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class DatabaseManager {

    public static void main(String[] args) {
        List<String> csvRows = Arrays.asList(
                "CustomerRef,CustomerName,AddressLine1,AddressLine2,Town,County,Country,Postcode",
                "123,John Doe,123 Main St,,City,County1,Country1,12345"
                // Add more rows as needed
        );

        saveToDatabase(csvRows);
    }

    private static void saveToDatabase(List<String> csvRows) {
        String jdbcUrl = "jdbc:mysql://your-database-host:3306/your-database";
        String username = "your-username";
        String password = "your-password";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            for (String row : csvRows.subList(1, csvRows.size())) { // Skip header row
                List<String> fields = Arrays.asList(row.split(","));
                String sql = "INSERT INTO your_table (CustomerRef, CustomerName, AddressLine1, AddressLine2, Town, County, Country, Postcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    for (int i = 0; i < fields.size(); i++) {
                        statement.setString(i + 1, fields.get(i));
                    }

                    statement.executeUpdate();
                }
            }
            System.out.println("Data saved to database successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
