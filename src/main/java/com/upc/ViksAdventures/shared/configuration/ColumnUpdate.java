package com.upc.ViksAdventures.shared.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class ColumnUpdate {

    // Inyectar propiedades desde el application.properties
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @PostConstruct
    public void updateColumnSize() {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             Statement statement = connection.createStatement()) {
            String tableName = "questions";
            String columnName = "question_text";
            String checkColumnSQL = "SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS " +
                                    "WHERE TABLE_NAME = '" + tableName +
                                    "' AND COLUMN_NAME = '" + columnName + "'";
            ResultSet resultSet = statement.executeQuery(checkColumnSQL);
            if (resultSet.next()) {
                String columnType = resultSet.getString("DATA_TYPE");
                if ("text".equalsIgnoreCase(columnType)) {
                    System.out.println("La columna " + columnName + " ya es de tipo TEXT. No es necesario modificarla.");
                } else {
                    String alterTableSQL = "ALTER TABLE " + tableName + " MODIFY " + columnName + " TEXT";
                    statement.executeUpdate(alterTableSQL);
                    System.out.println("La columna " + columnName + " ha sido modificada a TEXT.");
                }
            }
        } catch (Exception e) {
            System.out.println("Hubo un error." + e.getMessage());
        }
    }
}