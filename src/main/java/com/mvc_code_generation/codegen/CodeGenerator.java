package com.mvc_code_generation.codegen;

import java.io.IOException;
import java.util.Arrays;

public class CodeGenerator {

    public static void main(String[] args) throws IOException {
        String basePackage = "com.mvc_code_generation"; // You can make this dynamic as needed

        String[] ddls = {
        """
        CREATE TABLE User (
        id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(255) NOT NULL,
        email VARCHAR(255) NOT NULL,
        password VARCHAR(255) NOT NULL,
        isActive BOOLEAN DEFAULT FALSE,
        otp VARCHAR(255),
        isVerified BOOLEAN DEFAULT FALSE
        );
        """
        };


        for (String ddl : ddls) {
            String tableName = parseTableName(ddl);
            String[] columns = parseColumns(ddl);

            EntityGenerator.generateEntity(basePackage, tableName, columns);
            RepositoryGenerator.generateRepository(basePackage, tableName);
            ServiceGenerator.generateService(basePackage, tableName);
            ControllerGenerator.generateController(basePackage, tableName);
        }
    }

    private static String parseTableName(String ddl) {
        String[] parts = ddl.split("\\s+");
        return parts[2];
    }

    private static String[] parseColumns(String ddl) {
        String columnsPart = ddl.substring(ddl.indexOf("(") + 1, ddl.lastIndexOf(")"));
        return Arrays.stream(columnsPart.split(",\\n")) // Split by new line and comma
                .map(String::trim)
                .toArray(String[]::new);
    }
}
